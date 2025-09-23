package org.arsen.forex.service;

import org.arsen.forex.service.creation.account.AccountCreationParameters;
import org.arsen.forex.service.creation.account.AccountCreationResult;

public interface AccountService {

    AccountCreationResult create(AccountCreationParameters parameters);
}
