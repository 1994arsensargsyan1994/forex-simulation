package org.arsen.forex.service.utils;

import java.util.UUID;

public final class AccountNumberGenerator {

    // in future change uuid to normal account number solution
    public static String generate() {
        return UUID.randomUUID().toString();
    }

    private AccountNumberGenerator() {
        throw new UnsupportedOperationException();
    }
}