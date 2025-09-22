package org.arsen.forex.simulation.service.creation.account;

import org.arsen.forex.simulation.common.CurrencyType;

import java.math.BigDecimal;

public record AccountCreationParameters(Long customerId, CurrencyType currency, BigDecimal balance) {
}
