package org.arsen.forex.service.utils;

import org.arsen.forex.persistence.rate.PersistentCurrencyRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Utility class for generating random currency rates.
 * <p>
 * Each new rate is based on the old one, with a random change of ±5%.
 * The result is rounded to 6 decimal places.
 * </p>
 */
public final class RandomRateGenerator {

    private final static Random random = new Random();

    /**
     * Generate a new rate with a small random change.
     *
     * @param rate the current currency rate
     * @return a new rate value within ±5% of the old one
     */
    public static BigDecimal generate(PersistentCurrencyRate rate) {
        var old = rate.getRate();
        double factor = 0.95 + (0.10 * random.nextDouble()); // +/-5%
        return old.multiply(BigDecimal.valueOf(factor))
                .setScale(6, RoundingMode.HALF_UP);
    }

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws UnsupportedOperationException always, because this is a utility class
     */
    private RandomRateGenerator() {
        throw new UnsupportedOperationException();
    }
}
