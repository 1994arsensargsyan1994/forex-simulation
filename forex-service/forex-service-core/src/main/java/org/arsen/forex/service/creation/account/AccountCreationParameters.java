package org.arsen.forex.service.creation.account;

import org.arsen.forex.common.CurrencyType;

import java.math.BigDecimal;

public record AccountCreationParameters(Long customerId, CurrencyType currency, BigDecimal balance) {
}
