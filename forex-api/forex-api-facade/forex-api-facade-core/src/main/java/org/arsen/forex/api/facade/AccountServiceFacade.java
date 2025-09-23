package org.arsen.forex.api.facade;

import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.response.AccountCreationResponse;

public interface AccountServiceFacade {

    AccountCreationResponse create(Long customerId, AccountCreationRequest request);

}
