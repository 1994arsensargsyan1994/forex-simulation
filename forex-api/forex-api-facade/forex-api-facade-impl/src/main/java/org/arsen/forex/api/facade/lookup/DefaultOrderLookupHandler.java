package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.facade.converter.OrderDetailsConverter;
import org.arsen.forex.api.model.response.LookupOrderDetailsResponse;
import org.arsen.forex.service.OrderService;
import org.arsen.forex.service.lookup.LookupOrderDetailsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultOrderLookupHandler implements OrderLookupHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderLookupHandler.class);

    private final OrderService orderService;
    private final OrderDetailsConverter detailsConverter;

    public DefaultOrderLookupHandler(OrderService orderService, OrderDetailsConverter detailsConverter) {
        this.orderService = orderService;
        this.detailsConverter = detailsConverter;
    }

    @Override
    public LookupOrderDetailsResponse lookupDetails(Long id) {
        final LookupOrderDetailsResult lookupDetailsResult = orderService.lookupDetails(id);
        final LookupOrderDetailsResponse response = new LookupOrderDetailsResponse();

        if (lookupDetailsResult.hasFailures()) {
            logger.warn("Lookup Order list details failed with: {}.", lookupDetailsResult.failures());
            response.setResultFailures(lookupDetailsResult.failures());
            return response;
        }
        response.setDetails(detailsConverter.convert(lookupDetailsResult.getDetails()));
        return response;
    }
}