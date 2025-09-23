package org.arsen.forex.api.facade;

import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.response.AccountCreationResponse;
import org.arsen.forex.api.model.response.LookupAccountResponse;

public interface AccountServiceFacade {

    AccountCreationResponse create(Long customerId, AccountCreationRequest request);

    LookupAccountResponse lookup(Long customerId);
}
