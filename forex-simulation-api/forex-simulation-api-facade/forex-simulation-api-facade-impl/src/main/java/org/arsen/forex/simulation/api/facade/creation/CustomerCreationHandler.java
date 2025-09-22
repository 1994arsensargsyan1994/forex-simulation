package org.arsen.forex.simulation.api.facade.creation;

import org.arsen.forex.simulation.api.model.request.CustomerCreationRequest;
import org.arsen.forex.simulation.api.model.response.CustomerCreationResponse;

public interface CustomerCreationHandler {

    CustomerCreationResponse create(final CustomerCreationRequest request);
}
