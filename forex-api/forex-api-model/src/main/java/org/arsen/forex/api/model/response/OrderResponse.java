package org.arsen.forex.api.model.response;

import org.arsen.forex.common.OrderStatus;
import org.arsen.forex.common.api.model.FailureAwareResponse;

public class OrderResponse extends FailureAwareResponse {

    private String idempotencyKey;
    private OrderStatus status;
    private boolean created;

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

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }
}