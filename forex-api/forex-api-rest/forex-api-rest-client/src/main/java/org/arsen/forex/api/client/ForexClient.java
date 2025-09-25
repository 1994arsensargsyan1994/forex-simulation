package org.arsen.forex.api.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.request.CustomerCreationRequest;
import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.api.model.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "forexClient", url = "${forex.service.url}")
public interface ForexClient {

    @PostMapping(value = "/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerCreationResponse createCustomer(@NotNull @Valid @RequestBody CustomerCreationRequest request);

    @GetMapping(path = "/customer/{id}/details",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    LookupCustomerDetailsResponse lookupCustomerDetails(@PathVariable("id") @NotNull @PositiveOrZero Long id);

    @PostMapping(value = "/customer/{customerId}/account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AccountCreationResponse createAccount(
            @PathVariable(name = "customerId") Long customerId,
            @NotNull @Valid @RequestBody AccountCreationRequest request
    );

    @GetMapping(value = "/customer/{customerId}/account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    LookupAccountResponse lookupAccounts(@PathVariable(name = "customerId") Long customerId);

    @PostMapping("/order")
    OrderResponse createOrder(@RequestHeader("Idempotency-Key") String idempotencyKey,
                              @RequestBody OrderRequest request
    );

    @GetMapping(path = "/order/{id}/details",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    LookupOrderDetailsResponse lookupOrderDetails(@PathVariable("id") @NotNull @PositiveOrZero Long id);

    @GetMapping(path = "/rate", consumes = MediaType.APPLICATION_JSON_VALUE)
    LookupRatesResponse lookupRates();
}