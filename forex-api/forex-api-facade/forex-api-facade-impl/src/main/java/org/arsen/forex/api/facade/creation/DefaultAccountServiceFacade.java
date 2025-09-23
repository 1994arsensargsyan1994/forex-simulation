package org.arsen.forex.api.facade.creation;

import org.arsen.forex.api.facade.AccountServiceFacade;
import org.arsen.forex.api.facade.lookup.AccountLookupHandler;
import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.response.AccountCreationResponse;
import org.arsen.forex.api.model.response.LookupAccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultAccountServiceFacade implements AccountServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAccountServiceFacade.class);

    private final AccountCreationHandler accountCreationHandler;
    private final AccountLookupHandler accountLookupHandler;

    DefaultAccountServiceFacade(AccountCreationHandler accountCreationHandler, AccountLookupHandler accountLookupHandler) {
        this.accountCreationHandler = accountCreationHandler;
        this.accountLookupHandler = accountLookupHandler;
    }

    @Override
    public AccountCreationResponse create(Long customerId, AccountCreationRequest request) {
        Assert.notNull(customerId, "Null was passed as an argument for parameter 'customerId'.");
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Create Account. Request: {} and id {}.", request, customerId);
        return accountCreationHandler.create(customerId, request);
    }

    @Override
    public LookupAccountResponse lookup(Long customerId) {
        Assert.notNull(customerId, "Null was passed as an argument for parameter 'customerId'.");
        logger.debug("Lookup Account. Customer id: {}.", customerId);
        return accountLookupHandler.lookup(customerId);
    }
}
