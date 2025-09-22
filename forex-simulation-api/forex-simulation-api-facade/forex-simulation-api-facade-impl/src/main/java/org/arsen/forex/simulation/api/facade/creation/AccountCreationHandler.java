package org.arsen.forex.simulation.api.facade.creation;

import org.arsen.forex.simulation.api.model.request.AccountCreationRequest;
import org.arsen.forex.simulation.api.model.response.AccountCreationResponse;

public interface AccountCreationHandler {

    AccountCreationResponse create(Long customerId, AccountCreationRequest request);
}
