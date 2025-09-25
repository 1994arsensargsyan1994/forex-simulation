package org.arsen.forex.api.model.response;

import org.arsen.forex.common.OrderStatus;
import org.arsen.forex.common.api.model.FailureAwareResponse;

public class OrderResponse extends FailureAwareResponse {

    private Long id;
    private String idempotencyKey;
    private OrderStatus status;
    private String failureReason;
    private Boolean created;

    public OrderResponse() {
        super();
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

    public Boolean getCreated() {
        return created;
    }

    public void setCreated(Boolean created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getFailureReason() {
        return failureReason;
    }
}