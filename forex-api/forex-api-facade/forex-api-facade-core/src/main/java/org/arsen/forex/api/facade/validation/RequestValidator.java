package org.arsen.forex.api.facade.validation;

import org.arsen.forex.api.model.common.ValidatableRequest;

public interface RequestValidator<V extends ValidatableRequest> {

    default ValidationResult validate(final V request) {
        return ValidationResult.success();
    }
}