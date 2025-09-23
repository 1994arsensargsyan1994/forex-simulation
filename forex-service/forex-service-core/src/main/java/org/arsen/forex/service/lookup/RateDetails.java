package org.arsen.forex.service.lookup;

import org.arsen.forex.common.CurrencyType;

import java.math.BigDecimal;

public interface RateDetails {

    Long id();

    CurrencyType from();

    CurrencyType to();

    BigDecimal rate();
}