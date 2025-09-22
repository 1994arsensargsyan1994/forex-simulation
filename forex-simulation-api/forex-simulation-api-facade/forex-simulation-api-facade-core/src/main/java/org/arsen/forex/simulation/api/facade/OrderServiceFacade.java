package org.arsen.forex.simulation.api.facade;

import org.arsen.forex.simulation.api.model.request.OrderRequest;
import org.arsen.forex.simulation.api.model.response.OrderResponse;

public interface OrderServiceFacade {

    OrderResponse create(String idempotencyKey, OrderRequest request);
}