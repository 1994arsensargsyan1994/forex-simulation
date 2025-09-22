package org.arsen.forex.simulation.api.model.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.common.api.model.FailureAwareResponse;

public class CustomerCreationResponse extends FailureAwareResponse {

    private Long customerId;

    public CustomerCreationResponse() {
        super();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("customerId", getCustomerId())
                .toString();
    }
}
