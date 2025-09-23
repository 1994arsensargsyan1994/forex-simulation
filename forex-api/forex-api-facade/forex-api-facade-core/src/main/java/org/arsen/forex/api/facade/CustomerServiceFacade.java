package org.arsen.forex.api.facade;

import org.arsen.forex.api.model.request.CustomerCreationRequest;
import org.arsen.forex.api.model.response.CustomerCreationResponse;
import org.arsen.forex.api.model.response.LookupCustomerDetailsResponse;

public interface CustomerServiceFacade {

    CustomerCreationResponse create(CustomerCreationRequest request);

    LookupCustomerDetailsResponse lookupDetails(Long id);
}
