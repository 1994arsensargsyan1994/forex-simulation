package org.arsen.forex.simulation.service.creation.account;

import org.arsen.forex.simulation.common.CurrencyType;

public record AccountCreationParameters(Long customerId, CurrencyType currency) {
}
