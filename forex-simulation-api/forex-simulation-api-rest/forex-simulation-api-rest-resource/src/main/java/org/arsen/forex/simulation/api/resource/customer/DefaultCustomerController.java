package org.arsen.forex.simulation.api.resource.customer;

import org.arsen.forex.simulation.api.facade.CustomerServiceFacade;
import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.response.CustomerCreationResponse;
import org.arsen.forex.simulation.api.model.response.LookupCustomerDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
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
