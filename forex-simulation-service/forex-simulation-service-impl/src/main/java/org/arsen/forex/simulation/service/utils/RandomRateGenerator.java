package org.arsen.forex.simulation.service.utils;

import org.arsen.forex.simulation.persistence.rate.PersistentCurrencyRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public final class RandomRateGenerator {

    private final static Random random = new Random();

    public static BigDecimal generate(PersistentCurrencyRate rate) {
        var old = rate.getRate();
        double factor = 0.95 + (0.10 * random.nextDouble()); // +/-5%
        return old.multiply(BigDecimal.valueOf(factor))
                .setScale(6, RoundingMode.HALF_UP);
    }

    private RandomRateGenerator() {
        throw new UnsupportedOperationException();
    }
}
