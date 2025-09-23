package org.arsen.forex.api.facade.validation;

import org.arsen.forex.api.model.request.OrderRequest;
import org.arsen.forex.service.order.OrderResultFailure;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
class OrderRequestValidator implements RequestValidator<OrderRequest> {

    @Override
    public ValidationResult validate(OrderRequest request) {
        Assert.notNull(request, "Request must not be null");

        final List<OrderResultFailure> failures = new ArrayList<>();

        final String fromAccountId = request.getFromAccountId();
        final String toAccountId = request.getToAccountId();

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            failures.add(OrderResultFailure.INVALID_AMOUNT);
        }

        if (fromAccountId.equals(toAccountId)) {
            failures.add(OrderResultFailure.SAME_ACCOUNT);
        }

        return ValidationResult.of(failures);
    }
}