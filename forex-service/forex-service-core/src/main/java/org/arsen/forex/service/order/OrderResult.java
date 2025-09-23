package org.arsen.forex.service.order;

import org.arsen.forex.common.OrderStatus;
import org.arsen.forex.common.Result;

import java.util.Collection;

public class OrderResult implements Result<OrderResultFailure> {

    private String idempotencyKey;
    private OrderStatus status;
    private boolean created;

    private final Collection<OrderResultFailure> failures;

    public OrderResult(final String idempotencyKey, OrderStatus status , boolean created) {
        this.idempotencyKey = idempotencyKey;
        this.created = created;
        this.status = status;
        this.failures = null;
    }

    public OrderResult(final Collection<OrderResultFailure> failures) {
        this.idempotencyKey = null;
        this.created = false;
        this.status = OrderStatus.FAILED;
        this.failures = failures;
    }

    @Override
    public Collection<OrderResultFailure> failures() {
        return failures;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }
}