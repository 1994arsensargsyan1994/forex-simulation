package org.arsen.forex.api.facade.validation;


import org.arsen.forex.common.Failure;
import org.arsen.forex.common.FailureDto;

import java.util.Collection;
import java.util.List;

public interface ValidationResult {

    ValidationResult SUCCESS_RESULT = new ValidationSuccess();

    static ValidationResult success() {
        return SUCCESS_RESULT;
    }

    default List<FailureDto> failures() {
        return List.of();
    }

    static <F extends Failure> ValidationResult of(final Collection<F> failures) {
        if (failures == null || failures.isEmpty()) {
            return ValidationResult.success();
        }
        return new ValidationFailed(
                failures.stream()
                        .map(failure -> new FailureDto(failure.code(), failure.reason())).toList()
        );
    }

    default boolean hasFailures() {
        return !failures().isEmpty();
    }

    static <F extends Failure> ValidationResult failedWith(final F failure) {
        if (failure == null) {
            throw new IllegalArgumentException("Null was passed as an argument for parameter 'failure'.");
        }
        return new ValidationFailed(List.of(new FailureDto(failure.code(), failure.reason())));
    }
}
