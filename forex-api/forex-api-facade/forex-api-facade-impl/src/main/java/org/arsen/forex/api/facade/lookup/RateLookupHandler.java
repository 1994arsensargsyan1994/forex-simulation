package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.model.response.LookupRatesResponse;

public interface RateLookupHandler {

    LookupRatesResponse lookup();
}