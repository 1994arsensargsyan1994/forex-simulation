package org.arsen.forex.service.utils;

import java.util.UUID;

/**
 * Utility class for generating account numbers.
 * <p>
 * Currently uses random UUIDs, but can be replaced
 * with a custom numbering strategy in the future.
 * </p>
 */
public final class AccountNumberGenerator {

    /**
     * Generate a new unique account number.
     *
     * @return account number as a string
     */
    public static String generate() {
        return UUID.randomUUID().toString();
    }

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws UnsupportedOperationException always, because this is a utility class
     */
    private AccountNumberGenerator() {
        throw new UnsupportedOperationException();
    }
}