package org.arsen.forex.simulation.service.creation.account;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.common.Failure;

public enum AccountCreationFailure implements Failure {

    ACCOUNT_ALREADY_EXISTS_FOR_TYPE("failure.account.already.exists", "Another Account already exists for type"),
    CUSTOMER_NOT_FOUND("failure.account.customer.not.found", "Customer Not found");

    private final String code;

    private final String reason;

    AccountCreationFailure(final String code, final String reason) {
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
