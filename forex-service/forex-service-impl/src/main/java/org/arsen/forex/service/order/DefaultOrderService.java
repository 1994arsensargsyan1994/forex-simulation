package org.arsen.forex.service.order;

import org.arsen.forex.common.OrderStatus;
import org.arsen.forex.persistence.account.AccountRepository;
import org.arsen.forex.persistence.order.OrderRepository;
import org.arsen.forex.persistence.order.PersistentOrder;
import org.arsen.forex.persistence.rate.RateRepository;
import org.arsen.forex.service.OrderService;
import org.arsen.forex.service.lookup.LookupOrderDetailsResult;
import org.arsen.forex.service.lookup.account.AccountLookupService;
import org.arsen.forex.service.lookup.details.ImmutableOrderDetailsAdapter;
import org.arsen.forex.service.lookup.order.OrderLookupService;
import org.arsen.forex.service.order.validator.OrderCreationContext;
import org.arsen.forex.service.order.validator.OrderValidator;
import org.arsen.forex.service.rate.ExchangeService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
class DefaultOrderService implements OrderService {

    private final List<OrderValidator> commonValidators;
    private final AccountLookupService accountLookupService;
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;
    private final RateRepository rateRepository;
    private final ExchangeService exchangeService;
    private final AccountRepository accountRepository;

    DefaultOrderService(List<OrderValidator> commonValidators,
                        AccountLookupService accountLookupService,
                        OrderLookupService orderLookupService, OrderRepository orderRepository,
                        RateRepository rateRepository, ExchangeService exchangeService,
                        AccountRepository accountRepository
    ) {
        this.commonValidators = commonValidators;
        this.accountLookupService = accountLookupService;
        this.orderLookupService = orderLookupService;
        this.orderRepository = orderRepository;
        this.rateRepository = rateRepository;
        this.exchangeService = exchangeService;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public OrderResult order(OrderParameters parameters) {

        var existingTransfer = orderRepository.findByIdempotencyKey(parameters.idempotencyKey());
        if (existingTransfer.isPresent()) {
            var order = existingTransfer.get();

            return order.isSuccess()
                    ? OrderResult.complied(order.id(), order.getIdempotencyKey(), false)
                    : OrderResult.businessFailed(order.id(), order.getIdempotencyKey(), order.getFailedReason(), false);
        }

        var failures = new ArrayList<OrderResultFailure>();
        var fromAccountOpt = accountLookupService.lookup(parameters.sourceAccountId());
        var toAccountOpt = accountLookupService.lookup(parameters.targetAccountId());

        if (fromAccountOpt.isEmpty()) failures.add(OrderResultFailure.ACCOUNT_NOT_FOUND);
        if (toAccountOpt.isEmpty()) failures.add(OrderResultFailure.ACCOUNT_NOT_FOUND);
        if (!failures.isEmpty()) return OrderResult.failed(failures);

        final var fromAccount = fromAccountOpt.get();
        final var toAccount = toAccountOpt.get();
        final var customer = fromAccount.getCustomer();

        final var ctx = new OrderCreationContext(parameters, customer, fromAccount, toAccount);

        for (var validator : commonValidators) {
            validator.validate(ctx, failures);
        }

        if (!failures.isEmpty()) {
            final PersistentOrder order = new PersistentOrder(
                    parameters.idempotencyKey(), fromAccount, toAccount, parameters.amount(), BigDecimal.ZERO, OrderStatus.FAILED
            );
            String reasonsString = getFailedReasonsString(failures);
            order.setFailedReason(reasonsString);
            orderRepository.save(order);
            return OrderResult.businessFailed(order.id(), order.getIdempotencyKey(), order.getFailedReason(), true);
        }

        final var currencyRate = rateRepository.findByCurrencyFromAndCurrencyTo(fromAccount.getCurrency(), toAccount.getCurrency());

        fromAccount.debit(parameters.amount());
        accountRepository.save(fromAccount);
        toAccount.credit(exchangeService.exchange(parameters.amount(), currencyRate.getRate()));
        accountRepository.save(toAccount);

        final var order = new PersistentOrder(
                parameters.idempotencyKey(), fromAccount, toAccount, parameters.amount(), currencyRate.getRate(), OrderStatus.COMPLETED
        );
        orderRepository.save(order);

        return OrderResult.complied(order.id(), order.getIdempotencyKey(), true);
    }

    @Override
    public LookupOrderDetailsResult lookupDetails(Long id) {
        Assert.notNull(id, "Null was passed as an argument for parameter 'id'.");
        return orderLookupService.lookup(id)
                .map(ImmutableOrderDetailsAdapter::new)
                .map(LookupOrderDetailsResult::new)
                .orElseGet(LookupOrderDetailsResult::notFound);
    }

    private String getFailedReasonsString(ArrayList<OrderResultFailure> failures) {
        return failures.stream()
                .map(OrderResultFailure::name)
                .collect(Collectors.joining(","));
    }
}
