package org.arsen.forex.api.facade.order;

import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.api.model.response.OrderResponse;

public interface OrderCreationHandler {

    OrderResponse order(String idempotencyKey, OrderRequest request);
}
