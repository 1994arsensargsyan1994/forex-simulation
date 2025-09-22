package org.arsen.forex.simulation.common.api.model;


import org.arsen.forex.simulation.common.FailureDto;

import java.util.Collection;
import java.util.List;

public interface Response {

    default Collection<FailureDto> failures() {
        return List.of();
    }

    default boolean isSuccessful() {
        final Collection<FailureDto> failures = failures();
        return failures == null || failures.isEmpty();
    }
}