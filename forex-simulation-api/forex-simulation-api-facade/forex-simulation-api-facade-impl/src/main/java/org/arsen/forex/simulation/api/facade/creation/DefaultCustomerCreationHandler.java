package org.arsen.forex.simulation.api.facade.creation;

import org.arsen.forex.simulation.api.facade.validation.RequestValidator;
import org.arsen.forex.simulation.api.facade.validation.ValidationResult;
import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.response.CustomerCreationResponse;
import org.arsen.forex.simulation.service.CustomerService;
import org.arsen.forex.simulation.service.creation.customer.CustomerCreationParameters;
import org.arsen.forex.simulation.service.creation.customer.CustomerCreationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultCustomerCreationHandler implements CustomerCreationHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCustomerCreationHandler.class);

    private final RequestValidator<CustomerCreationRequest> requestValidator;
    private final CustomerService customerService;

    DefaultCustomerCreationHandler(RequestValidator<CustomerCreationRequest> requestValidator, CustomerService customerService) {
        this.requestValidator = requestValidator;
        this.customerService = customerService;
    }

    @Override
    public CustomerCreationResponse create(CustomerCreationRequest request) {
        Assert.notNull(request, "Request must not be null");

        final ValidationResult validationResult = requestValidator.validate(request);
        final CustomerCreationResponse response = new CustomerCreationResponse();

        if (validationResult.hasFailures()) {
            logger.warn("Create Customer validation failed with: {}.", validationResult.failures());
            response.setFailures(validationResult.failures());
            return response;
        }

        final CustomerCreationResult result = customerService.create(new CustomerCreationParameters(
                request.username(), request.email(), request.dateOfBirth()
        ));

        if (result.hasFailures()) {
            response.setResultFailures(result.failures());
            logger.warn("Create Customer validation failed with: {}.", result.failures());
            return response;
        }

        response.setCustomerId(result.getId());
        return response;
    }
}
