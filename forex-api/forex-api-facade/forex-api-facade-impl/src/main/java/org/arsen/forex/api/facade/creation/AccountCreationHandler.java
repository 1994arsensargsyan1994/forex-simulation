package org.arsen.forex.api.facade.creation;

import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.response.AccountCreationResponse;

public interface AccountCreationHandler {

    AccountCreationResponse create(Long customerId, AccountCreationRequest request);
}
