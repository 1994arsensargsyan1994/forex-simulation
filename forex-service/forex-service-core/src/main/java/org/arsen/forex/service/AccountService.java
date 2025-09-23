package org.arsen.forex.service;

import org.arsen.forex.service.creation.account.AccountCreationParameters;
import org.arsen.forex.service.creation.account.AccountCreationResult;
import org.arsen.forex.service.lookup.LookupAccountResult;

public interface AccountService {

    AccountCreationResult create(AccountCreationParameters parameters);

    LookupAccountResult lookupAll(Long customerId);
}
