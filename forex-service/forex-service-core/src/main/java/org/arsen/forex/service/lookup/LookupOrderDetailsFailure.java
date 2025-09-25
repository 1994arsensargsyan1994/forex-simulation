package org.arsen.forex.service.lookup;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.common.Failure;

public enum LookupOrderDetailsFailure implements Failure {

    ORDER_NOT_FOUND("failure.order.not.found", "Order not found.");

    private final String code;

    private final String reason;

    LookupOrderDetailsFailure(final String code, final String reason) {
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
