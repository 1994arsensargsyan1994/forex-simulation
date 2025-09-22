package org.arsen.forex.simulation.api.facade.order;

import org.arsen.forex.simulation.api.facade.OrderServiceFacade;
import org.arsen.forex.simulation.api.model.request.OrderRequest;
import org.arsen.forex.simulation.api.model.response.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultOrderServiceFacade implements OrderServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderServiceFacade.class);

    private final OrderCreationHandler orderCreationHandler;

    DefaultOrderServiceFacade(OrderCreationHandler orderCreationHandler) {
        this.orderCreationHandler = orderCreationHandler;
    }

    @Override
    public OrderResponse create(String idempotencyKey, OrderRequest request) {
        Assert.notNull(idempotencyKey, "Null was passed as an argument for parameter 'idempotencyKey'.");
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Create Order. Request: {} idempotencyKey: {}.", request, idempotencyKey);
        return orderCreationHandler.order(idempotencyKey, request);
    }
}
