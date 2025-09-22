package org.arsen.forex.simulation.api.facade;

import org.arsen.forex.simulation.api.model.request.AccountCreationRequest;
import org.arsen.forex.simulation.api.model.response.AccountCreationResponse;

public interface AccountServiceFacade {

    AccountCreationResponse create(Long customerId, AccountCreationRequest request);

}
