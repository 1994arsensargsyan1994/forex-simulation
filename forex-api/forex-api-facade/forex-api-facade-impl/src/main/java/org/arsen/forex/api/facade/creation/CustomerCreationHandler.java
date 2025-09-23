package org.arsen.forex.api.facade.creation;

import org.arsen.forex.api.model.request.CustomerCreationRequest;
import org.arsen.forex.api.model.response.CustomerCreationResponse;

public interface CustomerCreationHandler {

    CustomerCreationResponse create(final CustomerCreationRequest request);
}
