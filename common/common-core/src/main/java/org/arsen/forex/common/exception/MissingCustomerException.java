package org.arsen.forex.common.exception;

public class MissingCustomerException extends RuntimeException {

    public MissingCustomerException(final String message) {
        super(message);
    }
}