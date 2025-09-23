package org.arsen.forex.api.facade.creation;

import org.arsen.forex.api.facade.AccountServiceFacade;
import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.response.AccountCreationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultAccountServiceFacade implements AccountServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAccountServiceFacade.class);

    private final AccountCreationHandler accountCreationHandler;

    DefaultAccountServiceFacade(AccountCreationHandler accountCreationHandler) {
        this.accountCreationHandler = accountCreationHandler;
    }

    @Override
    public AccountCreationResponse create(Long customerId, AccountCreationRequest request) {
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Create Account. Request: {}.", request);
        return accountCreationHandler.create(customerId, request);
    }
}
