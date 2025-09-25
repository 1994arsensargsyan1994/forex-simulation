package org.arsen.forex.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.api.model.response.details.OrderDetailsDto;
import org.arsen.forex.common.api.model.FailureAwareResponse;

public class LookupOrderDetailsResponse extends FailureAwareResponse {

    @JsonProperty("details")
    private OrderDetailsDto details;

    public OrderDetailsDto getDetails() {
        return details;
    }

    public void setDetails(OrderDetailsDto details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof LookupOrderDetailsResponse that)) return false;

        return new EqualsBuilder()
                .append(details, that.details)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(details)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("details", details)
                .toString();
    }
}
