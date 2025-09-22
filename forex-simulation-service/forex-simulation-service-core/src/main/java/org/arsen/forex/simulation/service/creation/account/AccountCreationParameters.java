package org.arsen.forex.simulation.service.creation.account;

import org.arsen.forex.simulation.common.AccountType;
import org.arsen.forex.simulation.common.CurrencyType;

public record AccountCreationParameters(Long customerId, AccountType type, CurrencyType currency) {
}
