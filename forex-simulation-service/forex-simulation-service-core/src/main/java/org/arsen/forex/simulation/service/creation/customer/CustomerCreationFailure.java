package org.arsen.forex.simulation.service.creation.customer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.common.Failure;

public enum CustomerCreationFailure implements Failure {

    COSTUMER_ALREADY_EXISTS_WITH_USERNAME("failure.customer.already.exists", "Another Customer already exists whit username"),
    COSTUMER_ALREADY_EXISTS_WITH_EMAIL("failure.customer.already.exists", "Another Customer already exists whit email");

    private final String code;

    private final String reason;

    CustomerCreationFailure(final String code, final String reason) {
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
