package org.arsen.forex.simulation.common.exception;

public class MissingCustomerException extends RuntimeException {

    public MissingCustomerException(final String message) {
        super(message);
    }
}