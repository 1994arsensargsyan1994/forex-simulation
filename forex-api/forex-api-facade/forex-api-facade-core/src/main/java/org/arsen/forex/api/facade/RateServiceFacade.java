package org.arsen.forex.api.facade;

import org.arsen.forex.api.model.response.LookupRatesResponse;

public interface RateServiceFacade {

    LookupRatesResponse lookup();
}
