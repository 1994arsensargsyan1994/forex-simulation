package org.arsen.forex.simulation.service.lookup;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.common.Failure;

public enum LookupCustomerDetailsFailure implements Failure {

    URM_LIST_NOT_FOUND("failure.customer.not.found", "Customer not found.");

    private final String code;

    private final String reason;

    LookupCustomerDetailsFailure(final String code, final String reason) {
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
