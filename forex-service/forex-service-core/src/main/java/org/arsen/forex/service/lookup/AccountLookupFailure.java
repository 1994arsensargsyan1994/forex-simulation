package org.arsen.forex.service.lookup;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.common.Failure;

public enum AccountLookupFailure implements Failure {

    CUSTOMER_NOT_FOUND("failure.account.customer.not.found", "Customer Not found");

    private final String code;

    private final String reason;

    AccountLookupFailure(final String code, final String reason) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code())
                .append("reason", reason())
                .toString();
    }
}