package org.arsen.forex.simulation.api.facade;

import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.response.CustomerCreationResponse;
import org.arsen.forex.simulation.api.model.response.LookupCustomerDetailsResponse;

public interface CustomerServiceFacade {

    CustomerCreationResponse create(CustomerCreationRequest request);

    LookupCustomerDetailsResponse lookupDetails(Long id);
}
