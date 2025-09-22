package org.arsen.forex.simulation.api.facade.order;

import org.arsen.forex.simulation.api.facade.validation.RequestValidator;
import org.arsen.forex.simulation.api.facade.validation.ValidationResult;
import org.arsen.forex.simulation.api.model.request.OrderRequest;
import org.arsen.forex.simulation.api.model.response.OrderResponse;
import org.arsen.forex.simulation.service.OrderService;
import org.arsen.forex.simulation.service.order.OrderParameters;
import org.arsen.forex.simulation.service.order.OrderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultOrderCreationHandler implements OrderCreationHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderCreationHandler.class);

    private final RequestValidator<OrderRequest> validator;
    private final OrderService orderService;

    DefaultOrderCreationHandler(RequestValidator<OrderRequest> validator, OrderService orderService) {
        this.validator = validator;
        this.orderService = orderService;
    }

    @Override
    public OrderResponse order(String idempotencyKey, OrderRequest request) {
        Assert.notNull(idempotencyKey, "idempotencyKey must not be null");
        Assert.notNull(request, "request must not be null");

        final ValidationResult validationResult = validator.validate(request);
        final OrderResponse response = new OrderResponse();
        if (validationResult.hasFailures()) {
            logger.warn("Create Order validation failed with: {}.", validationResult.failures());
            response.setFailures(validationResult.failures());
            return response;
        }

        final OrderResult result = orderService.order(new OrderParameters(
                request.getFromAccountId(), request.getToAccountId(), request.getAmount(), idempotencyKey
        ));

        if (result.hasFailures()) {
            response.setResultFailures(result.failures());
            logger.warn("Create Order validation failed with: {}.", result.failures());
            return response;
        }

        response.setIdempotencyKey(result.getIdempotencyKey());
        response.setStatus(result.getStatus());
        response.setCreated(result.isCreated());

        return response;
    }
}
