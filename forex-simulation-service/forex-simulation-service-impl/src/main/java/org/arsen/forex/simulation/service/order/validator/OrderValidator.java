package org.arsen.forex.simulation.service.order.validator;

import org.arsen.forex.simulation.service.order.OrderResultFailure;
import org.springframework.stereotype.Component;

import java.util.List;

public interface OrderValidator {

    void validate(OrderCreationContext ctx, List<OrderResultFailure> outFailures);
}

/**
 * Basic request checks: idempotency key & amount validity.
 */
@Component
class BasicRequestValidator implements OrderValidator {

    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> failures) {
        if (ctx.parameters().idempotencyKey() == null || ctx.parameters().idempotencyKey().isBlank()) {
            failures.add(OrderResultFailure.INVALID_KEY);
        }
        if (ctx.parameters().amount() == null || ctx.parameters().amount().signum() <= 0) {
            failures.add(OrderResultFailure.INVALID_AMOUNT);
        }
    }
}

/**
 * Prevents transfers where from and to account IDs are identical.
 */
@Component
class SameAccountValidator implements OrderValidator {

    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> out) {
        if (ctx.parameters().sourceAccountId().equals(ctx.parameters().targetAccountId())) {
            out.add(OrderResultFailure.SAME_ACCOUNT);
        }
    }
}

/**
 * Prevents transfers that span across different customers.
 */
@Component
class SameCustomerValidator implements OrderValidator {
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
class SameCustomerDifferentCurrencyValidator implements OrderValidator {

    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> out) {
        if (ctx.from().getCurrency().equals(ctx.to().getCurrency())) {
            out.add(OrderResultFailure.CURRENCY_MISMATCH);
        }
    }
}

@Component
class SufficientFundsValidator implements OrderValidator {

    @Override
    public void validate(OrderCreationContext ctx, List<OrderResultFailure> out) {
        if (ctx.parameters().amount() == null) {
            out.add(OrderResultFailure.INVALID_AMOUNT);
            return;
        }

        if (ctx.from().getBalance().compareTo(ctx.parameters().amount()) < 0) {
            out.add(OrderResultFailure.INSUFFICIENT_FUNDS);
        }
    }
}