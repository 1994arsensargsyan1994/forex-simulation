package org.arsen.forex.service.rate;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Component
public class ExchangeService {

    public BigDecimal exchange(BigDecimal amount, BigDecimal rate) {
        Assert.notNull(amount, "Amount must not be null");
        Assert.notNull(rate, "Rate must not be null");
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, "Amount must be greater then zero");
        Assert.isTrue(rate.compareTo(BigDecimal.ZERO) > 0, "Rate must be greater then zero");
        return amount.multiply(rate);
    }
}