package org.arsen.forex.simulation.api.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.arsen.forex.simulation.api.model.request.AccountCreationRequest;
import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.request.OrderRequest;
import org.arsen.forex.simulation.api.model.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "forexClient", url = "${forex.service.url:http://localhost:8089}")
public interface ForexClient {

    @PostMapping(value = "/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerCreationResponse createCustomer(@NotNull @Valid @RequestBody CustomerCreationRequest request);

    @GetMapping(path = "/customer/{id}/details",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    LookupCustomerDetailsResponse lookupCustomerDetails(@PathVariable("id") @NotNull @Positive Long id);

    @PostMapping(value = "/account/{customerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AccountCreationResponse createAccount(
            @PathVariable(name = "customerId") Long customerId,
            @NotNull @Valid @RequestBody AccountCreationRequest request
    );


    @PostMapping("/order")
    OrderResponse createOrder(@RequestHeader("Idempotency-Key") String idempotencyKey,
                        @RequestBody OrderRequest request
    );

    @GetMapping(path = "/rate", consumes = MediaType.APPLICATION_JSON_VALUE)
    LookupRatesResponse lookupRates();
}