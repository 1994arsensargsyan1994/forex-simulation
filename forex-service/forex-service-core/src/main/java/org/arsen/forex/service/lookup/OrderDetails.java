package org.arsen.forex.service.lookup;

import org.arsen.forex.common.CurrencyType;
import org.arsen.forex.common.OrderStatus;

import java.math.BigDecimal;

public interface OrderDetails {

    CurrencyType currencyFrom();

    CurrencyType currencyTo();

    BigDecimal amount();

    BigDecimal rate();

    OrderStatus status();

    String failedReason();
}
