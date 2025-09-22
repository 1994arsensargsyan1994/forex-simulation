package org.arsen.forex.simulation.service.lookup;

import org.arsen.forex.simulation.common.Failure;
import org.arsen.forex.simulation.common.Result;

import java.util.Collection;

public interface LookupRateResult extends Result<Failure> {

    Integer count();

    Collection<RateDetails> details();

    static LookupRateResult of(final int count, final Collection<RateDetails> details) {
        if (count < 0) {
            throw new IllegalArgumentException("Null or negative number was passed as an argument for parameter 'count'.");
        }
        if (details == null) {
            throw new IllegalArgumentException("Null was passed as an argument for parameter 'details'.");
        }
        return new ImmutableRateResult(count, details);
    }
}