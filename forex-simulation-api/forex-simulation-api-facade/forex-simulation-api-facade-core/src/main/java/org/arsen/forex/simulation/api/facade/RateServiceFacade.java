package org.arsen.forex.simulation.api.facade;

import org.arsen.forex.simulation.api.model.response.LookupRatesResponse;

public interface RateServiceFacade {

    LookupRatesResponse lookup();
}
