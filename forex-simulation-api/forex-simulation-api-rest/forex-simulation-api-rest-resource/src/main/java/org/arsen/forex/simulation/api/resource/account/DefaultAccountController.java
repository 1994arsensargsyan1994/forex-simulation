package org.arsen.forex.simulation.api.resource.account;

import org.arsen.forex.simulation.api.facade.AccountServiceFacade;
import org.arsen.forex.simulation.api.model.request.AccountCreationRequest;
import org.arsen.forex.simulation.api.model.response.AccountCreationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
class DefaultAccountController implements AccountController {

    private final AccountServiceFacade serviceFacade;

    public DefaultAccountController(AccountServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public ResponseEntity<AccountCreationResponse> create(Long customerId, AccountCreationRequest request) {
        Assert.notNull(request, "request must not be null");
        Assert.notNull(customerId, "customerId must not be null");
        return ResponseEntity.ok(serviceFacade.create(customerId, request));
    }
}
