package org.arsen.forex.simulation.api.resource.order;

import org.arsen.forex.simulation.api.facade.OrderServiceFacade;
import org.arsen.forex.simulation.api.model.request.OrderRequest;
import org.arsen.forex.simulation.api.model.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
class DefaultOrderController implements OrderController {

    private final OrderServiceFacade orderServiceFacade;

    DefaultOrderController(OrderServiceFacade orderServiceFacade) {
        this.orderServiceFacade = orderServiceFacade;
    }

    @Override
    public ResponseEntity<OrderResponse> order(String idempotencyKey, OrderRequest request) {
        Assert.notNull(idempotencyKey, "idempotencyKey must not be null");
        Assert.notNull(request, "request must not be null");
        return ResponseEntity.ok(orderServiceFacade.create(idempotencyKey , request));
    }
}
