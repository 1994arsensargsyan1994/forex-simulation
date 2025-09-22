package org.arsen.forex.simulation.api.facade.lookup;

import org.arsen.forex.simulation.api.facade.RateServiceFacade;
import org.arsen.forex.simulation.api.model.response.LookupRatesResponse;
import org.springframework.stereotype.Component;

@Component
class DefaultRateServiceFacade implements RateServiceFacade {

    private final RateLookupHandler rateLookupHandler;

    DefaultRateServiceFacade(RateLookupHandler rateLookupHandler) {
        this.rateLookupHandler = rateLookupHandler;
    }

    @Override
    public LookupRatesResponse lookup() {
        return rateLookupHandler.lookup();
    }
}
