package org.arsen.forex.service.lookup;

import org.arsen.forex.common.CurrencyType;

import java.math.BigDecimal;

public interface AccountDetails {

    String number();

    CurrencyType currency();

    BigDecimal balance();

    boolean isDisabled();
}