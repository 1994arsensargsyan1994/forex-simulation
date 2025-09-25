package org.arsen.forex.service.order.validator;

import org.arsen.forex.service.order.OrderResultFailure;
import org.springframework.stereotype.Component;

import java.util.List;

public interface OrderValidator {

    void validate(OrderCreationContext ctx, List<OrderResultFailure> outFailures);
}

/**
 * Basic request checks: idempotency key & amount validity.
 */
@Component
class BasicOrderValidator implements OrderValidator {

    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> failures) {
        if (ctx.parameters().idempotencyKey() == null || ctx.parameters().idempotencyKey().isBlank()) {
            failures.add(OrderResultFailure.INVALID_KEY);
        }
    }
}

/**
 * Prevents transfers that span across different customers.
 */
@Component
class SameCustomerOrderValidator implements OrderValidator {
    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> out) {
        if (!ctx.customer().id().equals(ctx.from().getCustomer().id())
                || !ctx.customer().id().equals(ctx.to().getCustomer().id())) {
            out.add(OrderResultFailure.CROSS_CUSTOMER_NOT_ALLOWED);
        }
    }
}

/**
 * New: allows only same-customer transfers with DIFFERENT currencies.
 * Use this for forex/exchange scenarios.
 */
@Component
class SameCustomerDifferentCurrencyOrderValidator implements OrderValidator {

    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> out) {
        if (ctx.from().getCurrency().equals(ctx.to().getCurrency())) {
            out.add(OrderResultFailure.CURRENCY_MISMATCH);
        }
    }
}

@Component
class SufficientFundsOrderValidator implements OrderValidator {

    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> out) {
        if (ctx.from().getBalance().compareTo(ctx.parameters().amount()) < 0) {
            out.add(OrderResultFailure.INSUFFICIENT_FUNDS);
        }
    }
}