package org.arsen.forex.simulation.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.api.model.response.details.RatesDetailsDto;
import org.arsen.forex.simulation.common.api.model.FailureAwareResponse;

import java.util.Collection;

public class LookupRatesResponse extends FailureAwareResponse {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("rates")
    private Collection<RatesDetailsDto> rates;

    public LookupRatesResponse() {
        super();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    public Collection<RatesDetailsDto> getRates() {
        return rates;
    }

    public void setItems(final Collection<RatesDetailsDto> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LookupRatesResponse that)) {
            return false;
        }
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getCount(), that.getCount())
                .append(getRates(), that.getRates())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getCount())
                .append(getRates())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("count", getCount())
                .append("rates", getRates())
                .toString();
    }
}
