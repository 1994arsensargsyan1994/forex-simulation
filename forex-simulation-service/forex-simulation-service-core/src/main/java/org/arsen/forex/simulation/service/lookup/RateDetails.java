package org.arsen.forex.simulation.service.lookup;

import org.arsen.forex.simulation.common.CurrencyType;

import java.math.BigDecimal;

public interface RateDetails {

    Long id();

    CurrencyType from();

    CurrencyType to();

    BigDecimal rate();
}