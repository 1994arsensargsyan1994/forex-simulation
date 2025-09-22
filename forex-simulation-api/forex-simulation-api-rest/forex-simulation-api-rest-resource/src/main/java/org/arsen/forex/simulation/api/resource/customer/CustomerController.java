package org.arsen.forex.simulation.api.resource.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.response.CustomerCreationResponse;
import org.arsen.forex.simulation.api.model.response.LookupCustomerDetailsResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/customer")
public interface CustomerController {

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerCreationResponse> create(@Valid @RequestBody CustomerCreationRequest request);

    @GetMapping(path = "/{id}")
    ResponseEntity<LookupCustomerDetailsResponse> lookupDetails(@PathVariable(name = "id") @NotNull @Positive Long id);
}
