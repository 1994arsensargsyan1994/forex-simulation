package org.arsen.forex.simulation.service.order;

import org.arsen.forex.simulation.common.OrderStatus;
import org.arsen.forex.simulation.persistence.order.OrderRepository;
import org.arsen.forex.simulation.persistence.order.PersistentOrder;
import org.arsen.forex.simulation.persistence.rate.RateRepository;
import org.arsen.forex.simulation.service.OrderService;
import org.arsen.forex.simulation.service.lookup.account.AccountLookupService;
import org.arsen.forex.simulation.service.order.validator.OrderCreationContext;
import org.arsen.forex.simulation.service.order.validator.OrderValidator;
import org.arsen.forex.simulation.service.rate.ExchangeService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
class DefaultOrderService implements OrderService {

    private final List<OrderValidator> commonValidators;
    private final AccountLookupService accountLookupService;
    private final OrderRepository orderRepository;
    private final RateRepository rateRepository;
    private final ExchangeService exchangeService;

    DefaultOrderService(List<OrderValidator> commonValidators, AccountLookupService accountLookupService, OrderRepository orderRepository, RateRepository rateRepository, ExchangeService exchangeService) {
        this.commonValidators = commonValidators;
        this.accountLookupService = accountLookupService;
        this.orderRepository = orderRepository;
        this.rateRepository = rateRepository;
        this.exchangeService = exchangeService;
    }

    @Override
    @Transactional
    public OrderResult order(OrderParameters parameters) {

        var failures = new ArrayList<OrderResultFailure>();
        var fromAccountOpt = accountLookupService.lookup(parameters.sourceAccountId());
        var toAccountOpt = accountLookupService.lookup(parameters.targetAccountId());

        if (fromAccountOpt.isEmpty()) failures.add(OrderResultFailure.ACCOUNT_NOT_FOUND);
        if (toAccountOpt.isEmpty()) failures.add(OrderResultFailure.ACCOUNT_NOT_FOUND);
        if (!failures.isEmpty()) return new OrderResult(failures);

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
            order.setFailedReason(failures.get(0).name());
            orderRepository.save(order);
            return new OrderResult(failures);
        }

        final var currencyRate = rateRepository.findByCurrencyFromAndCurrencyTo(fromAccount.getCurrency(), toAccount.getCurrency());

        fromAccount.debit(parameters.amount());
        toAccount.credit(exchangeService.exchange(parameters.amount(), currencyRate.getRate()));

        final var order = new PersistentOrder(
                parameters.idempotencyKey(), fromAccount, toAccount, parameters.amount(), currencyRate.getRate(), OrderStatus.COMPLETED
        );
        orderRepository.save(order);

        return new OrderResult(order.getIdempotencyKey(), true);
    }
}
