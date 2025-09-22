package org.arsen.forex.simulation.api.facade.validation;

import org.arsen.forex.simulation.common.Failure;

public interface ValidationFailure extends Failure {

    default ValidationResult asFailedResult() {
        return ValidationResult.failedWith(this);
    }
}