package org.arsen.forex.simulation.service;

import org.arsen.forex.simulation.service.creation.account.AccountCreationParameters;
import org.arsen.forex.simulation.service.creation.account.AccountCreationResult;

public interface AccountService {

    AccountCreationResult create(AccountCreationParameters parameters);
}
