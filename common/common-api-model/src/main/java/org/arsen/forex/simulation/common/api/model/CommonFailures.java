package org.arsen.forex.simulation.common.api.model;

import org.arsen.forex.simulation.common.Failure;

public enum CommonFailures implements Failure {

    INTERNAL_SERVER_ERROR("failure.internal server.error", "Internal server error.");

    private final String code;

    private final String reason;

    CommonFailures(final String code, final String reason) {
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