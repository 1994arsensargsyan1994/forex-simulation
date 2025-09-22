package org.arsen.forex.simulation.api.facade.creation;

import org.arsen.forex.simulation.api.facade.validation.RequestValidator;
import org.arsen.forex.simulation.api.facade.validation.ValidationResult;
import org.arsen.forex.simulation.api.model.request.AccountCreationRequest;
import org.arsen.forex.simulation.api.model.response.AccountCreationResponse;
import org.arsen.forex.simulation.service.AccountService;
import org.arsen.forex.simulation.service.creation.account.AccountCreationParameters;
import org.arsen.forex.simulation.service.creation.account.AccountCreationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class DefaultAccountCreationHandler implements AccountCreationHandler {

    private final static Logger logger = LoggerFactory.getLogger(DefaultAccountCreationHandler.class);

    private final RequestValidator<AccountCreationRequest> validator;
    private final AccountService accountService;

    public DefaultAccountCreationHandler(RequestValidator<AccountCreationRequest> validator, AccountService accountService) {
        this.validator = validator;
        this.accountService = accountService;
    }

    @Override
    public AccountCreationResponse create(Long customerId, AccountCreationRequest request) {
        Assert.notNull(customerId, "customerId must not be null");
        Assert.notNull(request, "Request must not be null");

        final ValidationResult validationResult = validator.validate(request);
        final AccountCreationResponse response = new AccountCreationResponse();

        if (validationResult.hasFailures()) {
            logger.warn("Create Account validation failed with: {}.", validationResult.failures());
            response.setFailures(validationResult.failures());
            return response;
        }

        final AccountCreationResult result = accountService.create(new AccountCreationParameters(
                customerId, request.getType(), request.getCurrency()
        ));

        if (result.hasFailures()) {
            response.setResultFailures(result.failures());
            logger.warn("Create Account validation failed with: {}.", result.failures());
            return response;
        }

        response.setNumber(result.getNumber());
        return response;
    }
}
