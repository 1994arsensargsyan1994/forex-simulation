package org.arsen.forex.simulation.api.resource.customer;

import org.arsen.forex.simulation.api.facade.CustomerServiceFacade;
import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.response.CustomerCreationResponse;
import org.arsen.forex.simulation.api.model.response.LookupCustomerDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultCustomerController implements CustomerController {

    private final CustomerServiceFacade customerServiceFacade;

    DefaultCustomerController(CustomerServiceFacade customerServiceFacade) {
        this.customerServiceFacade = customerServiceFacade;
    }

    @Override
    public ResponseEntity<CustomerCreationResponse> create(CustomerCreationRequest request) {
        Assert.notNull(request, "request must not be null");
        return ResponseEntity.ok(customerServiceFacade.create(request));
    }

    @Override
    public ResponseEntity<LookupCustomerDetailsResponse> lookupDetails(Long id) {
        Assert.notNull(id, "id must not be null");
        return ResponseEntity.ok(customerServiceFacade.lookupDetails(id));
    }
}
