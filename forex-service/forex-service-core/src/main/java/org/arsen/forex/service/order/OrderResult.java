package org.arsen.forex.service.order;

import org.arsen.forex.common.OrderStatus;
import org.arsen.forex.common.Result;

import java.util.Collection;

public class OrderResult implements Result<OrderResultFailure> {

    private final Long id;
    private final String idempotencyKey;
    private final OrderStatus status;
    private final String failureReason;
    private final Boolean created;

    private final Collection<OrderResultFailure> failures;

    private OrderResult(Long id, String idempotencyKey, Boolean created) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.created = created;
        this.status = OrderStatus.COMPLETED;
        this.failures = null;
        this.failureReason = null;
    }

    private OrderResult(Long id, String idempotencyKey, String failureReason, Boolean created) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.created = created;
        this.status = OrderStatus.FAILED;
        this.failures = null;
        this.failureReason = failureReason;
    }

    private OrderResult(final Collection<OrderResultFailure> failures) {
        this.created = false;
        this.status = OrderStatus.FAILED;
        this.failures = failures;
        this.id = null;
        this.failureReason = null;
        this.idempotencyKey = null;
    }

    public static OrderResult complied(Long id, String idempotencyKey, Boolean created) {
        return new OrderResult(id, idempotencyKey, created);
    }

    public static OrderResult businessFailed(Long id, String idempotencyKey, String businessFailure, Boolean created) {
        return new OrderResult(id, idempotencyKey, businessFailure, created);
    }

    public static OrderResult failed(Collection<OrderResultFailure> failures) {
        return new OrderResult(failures);
    }

    @Override
    public Collection<OrderResultFailure> failures() {
        return failures;
    }

    public Long getId() {
        return id;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean isCreated() {
        return created;
    }

    public String getFailureReason() {
        return failureReason;
    }
}