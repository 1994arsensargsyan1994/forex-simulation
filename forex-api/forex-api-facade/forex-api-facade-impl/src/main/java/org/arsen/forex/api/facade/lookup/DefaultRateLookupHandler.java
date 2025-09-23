package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.facade.converter.RateDetailsConverter;
import org.arsen.forex.api.model.response.LookupRatesResponse;
import org.arsen.forex.service.RateService;
import org.arsen.forex.service.lookup.LookupRateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultRateLookupHandler implements RateLookupHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRateLookupHandler.class);

    private final RateService rateService;
    private final RateDetailsConverter listDetailsConverter;

    DefaultRateLookupHandler(RateService rateService, RateDetailsConverter listDetailsConverter) {
        this.rateService = rateService;
        this.listDetailsConverter = listDetailsConverter;
    }

    @Override
    public LookupRatesResponse lookup() {
        final LookupRateResult lookupResult = rateService.lookup();
        final LookupRatesResponse response = new LookupRatesResponse();
        if (lookupResult.hasFailures()) {
            logger.warn("Lookup Rate lists failed with: {}.", lookupResult.failures());
            response.setResultFailures(lookupResult.failures());
            return response;
        }

        response.setCount(lookupResult.count());
        response.setItems(listDetailsConverter.convertAll(lookupResult.details()));
        return response;
    }
}