package org.arsen.forex.api.facade.order;

import org.arsen.forex.api.facade.OrderServiceFacade;
import org.arsen.forex.api.facade.lookup.OrderLookupHandler;
import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.api.model.response.LookupOrderDetailsResponse;
import org.arsen.forex.api.model.response.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultOrderServiceFacade implements OrderServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderServiceFacade.class);

    private final OrderCreationHandler orderCreationHandler;
    private final OrderLookupHandler orderLookupHandler;

    DefaultOrderServiceFacade(OrderCreationHandler orderCreationHandler, OrderLookupHandler orderLookupHandler) {
        this.orderCreationHandler = orderCreationHandler;
        this.orderLookupHandler = orderLookupHandler;
    }

    @Override
    public OrderResponse create(String idempotencyKey, OrderRequest request) {
        Assert.notNull(idempotencyKey, "Null was passed as an argument for parameter 'idempotencyKey'.");
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Create Order. Request: {} idempotencyKey: {}.", request, idempotencyKey);
        return orderCreationHandler.order(idempotencyKey, request);
    }

    @Override
    public LookupOrderDetailsResponse lookupDetails(Long id) {
        Assert.notNull(id, "Null was passed as an argument for parameter 'id'.");
        logger.debug("Lookup Order. ID: {}.", id);
        return orderLookupHandler.lookupDetails(id);
    }
}
