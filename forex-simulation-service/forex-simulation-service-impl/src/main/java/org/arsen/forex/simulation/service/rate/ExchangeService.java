package org.arsen.forex.simulation.service.rate;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeService {

    public BigDecimal exchange(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate);
    }
}