package org.arsen.forex.service;

import org.arsen.forex.service.lookup.LookupRateResult;

public interface RateService {

    LookupRateResult lookup();
}
