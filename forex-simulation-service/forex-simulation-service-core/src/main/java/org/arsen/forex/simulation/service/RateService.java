package org.arsen.forex.simulation.service;

import org.arsen.forex.simulation.service.lookup.LookupRateResult;

public interface RateService {

    LookupRateResult lookup();
}
