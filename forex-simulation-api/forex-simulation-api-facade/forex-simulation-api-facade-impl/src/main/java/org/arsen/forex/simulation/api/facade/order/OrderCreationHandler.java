package org.arsen.forex.simulation.api.facade.order;

import org.arsen.forex.simulation.api.model.request.OrderRequest;
import org.arsen.forex.simulation.api.model.response.OrderResponse;

public interface OrderCreationHandler {

    OrderResponse order(String idempotencyKey, OrderRequest request);
}
