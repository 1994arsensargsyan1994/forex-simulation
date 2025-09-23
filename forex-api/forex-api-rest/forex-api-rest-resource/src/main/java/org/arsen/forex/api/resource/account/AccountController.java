package org.arsen.forex.api.resource.account;

import jakarta.validation.Valid;
import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.response.AccountCreationResponse;
import org.arsen.forex.api.model.response.LookupAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer/{customerId}/account")
public interface AccountController {

    @PostMapping()
    ResponseEntity<AccountCreationResponse> create(
            @PathVariable(name = "customerId") Long customerId,
            @Valid @RequestBody AccountCreationRequest request
    );

    @GetMapping()
    ResponseEntity<LookupAccountResponse> lookup(
            @PathVariable(name = "customerId") Long customerId
    );
}
