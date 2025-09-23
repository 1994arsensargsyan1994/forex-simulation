package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.facade.converter.AccountDetailsConverter;
import org.arsen.forex.api.model.response.LookupAccountResponse;
import org.arsen.forex.service.AccountService;
import org.arsen.forex.service.lookup.LookupAccountResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultAccountLookupHandler implements AccountLookupHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAccountLookupHandler.class);

    private final AccountService accountService;
    private final AccountDetailsConverter accountDetailsConverter;

    DefaultAccountLookupHandler(AccountService accountService, AccountDetailsConverter accountDetailsConverter) {
        this.accountService = accountService;
        this.accountDetailsConverter = accountDetailsConverter;
    }

    @Override
    public LookupAccountResponse lookup(Long customerId) {
        final LookupAccountResult lookupResult = accountService.lookupAll(customerId);
        final LookupAccountResponse response = new LookupAccountResponse();
        if (lookupResult.hasFailures()) {
            logger.warn("Lookup Account lists failed with: {}.", lookupResult.failures());
            response.setResultFailures(lookupResult.failures());
            return response;
        }

        response.setCount(lookupResult.count());
        response.setItems(accountDetailsConverter.convertAll(lookupResult.accounts()));
        return response;
    }
}
