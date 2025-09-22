package org.arsen.forex.simulation.api.facade.creation;

import org.arsen.forex.simulation.api.facade.CustomerServiceFacade;
import org.arsen.forex.simulation.api.facade.lookup.CustomerLookupHandler;
import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.response.CustomerCreationResponse;
import org.arsen.forex.simulation.api.model.response.LookupCustomerDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultCustomerServiceFacade implements CustomerServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCustomerServiceFacade.class);

    private final CustomerCreationHandler customerCreationHandler;

    private final CustomerLookupHandler customerLookupHandler;

    DefaultCustomerServiceFacade(CustomerCreationHandler customerCreationHandler, CustomerLookupHandler customerLookupHandler) {
        this.customerCreationHandler = customerCreationHandler;
        this.customerLookupHandler = customerLookupHandler;
    }

    @Override
    public CustomerCreationResponse create(CustomerCreationRequest request) {
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Create Customer. Request: {}.", request);
        return customerCreationHandler.create(request);
    }

    @Override
    public LookupCustomerDetailsResponse lookupDetails(Long id) {
        Assert.notNull(id, "Null was passed as an argument for parameter 'id'.");
        logger.debug("Lookup Customer details. Id: {}.", id);
        return customerLookupHandler.lookupDetails(id);
    }
}
