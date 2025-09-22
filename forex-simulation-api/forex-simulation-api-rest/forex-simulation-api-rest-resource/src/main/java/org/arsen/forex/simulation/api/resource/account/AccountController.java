package org.arsen.forex.simulation.api.resource.account;

import jakarta.validation.Valid;
import org.arsen.forex.simulation.api.model.request.AccountCreationRequest;
import org.arsen.forex.simulation.api.model.response.AccountCreationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account")
public interface AccountController {

    @PostMapping(path = "/{customerId}")
    ResponseEntity<AccountCreationResponse> create(
            @PathVariable(name = "customerId") Long customerId,
            @Valid @RequestBody AccountCreationRequest request
    );
}
