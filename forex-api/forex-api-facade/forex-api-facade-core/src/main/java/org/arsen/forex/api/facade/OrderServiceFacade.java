package org.arsen.forex.api.facade;

import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.api.model.response.LookupOrderDetailsResponse;
import org.arsen.forex.api.model.response.OrderResponse;

public interface OrderServiceFacade {

    OrderResponse create(String idempotencyKey, OrderRequest request);

    LookupOrderDetailsResponse lookupDetails(Long id);
}