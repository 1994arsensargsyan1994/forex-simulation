package org.arsen.forex.simulation.api.facade.lookup;

import org.arsen.forex.simulation.api.model.response.LookupRatesResponse;

public interface RateLookupHandler {

    LookupRatesResponse lookup();
}