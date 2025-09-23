package org.arsen.forex.api.facade.validation;

import org.arsen.forex.common.Failure;

public interface ValidationFailure extends Failure {

    default ValidationResult asFailedResult() {
        return ValidationResult.failedWith(this);
    }
}