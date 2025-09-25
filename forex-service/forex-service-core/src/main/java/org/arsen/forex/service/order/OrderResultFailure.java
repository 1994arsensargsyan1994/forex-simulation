package org.arsen.forex.service.order;

import org.arsen.forex.common.Failure;

public enum OrderResultFailure implements Failure {
    ACCOUNT_NOT_FOUND("failure.order.account.not.found", "Account Not found"),
    SAME_ACCOUNT("failure.order.same_account", "Source and target accounts must be different"),
    CROSS_CUSTOMER_NOT_ALLOWED("failure.order.cross_customer", "Cross-customer transfers are not allowed"),
    CURRENCY_MISMATCH("failure.order.currency_mismatch", "Source and target accounts have different currencies"),
    INSUFFICIENT_FUNDS("failure.order.insufficient_funds", "Insufficient funds in the source account"),
    INVALID_AMOUNT("failure.order.invalid_amount", "Transfer amount must be greater than zero"),
    INVALID_KEY("failure.order.invalid_key", "Idempotency key is invalid");

    private final String code;

    private final String reason;

    OrderResultFailure(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String reason() {
        return reason;
    }
}
