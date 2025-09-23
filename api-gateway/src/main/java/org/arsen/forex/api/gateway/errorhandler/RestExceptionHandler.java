package org.arsen.forex.api.gateway.errorhandler;

import jakarta.validation.ConstraintViolationException;
import org.arsen.forex.common.api.model.FailureAwareResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<FailureAwareResponse> onException(Exception ex);

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<FailureAwareResponse> constraintViolationFailed(ConstraintViolationException ex);
}
