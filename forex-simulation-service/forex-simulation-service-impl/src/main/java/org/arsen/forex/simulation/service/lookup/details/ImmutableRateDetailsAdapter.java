package org.arsen.forex.simulation.service.lookup.details;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.common.CurrencyType;
import org.arsen.forex.simulation.persistence.rate.PersistentCurrencyRate;
import org.arsen.forex.simulation.service.lookup.RateDetails;

import java.math.BigDecimal;

public final class ImmutableRateDetailsAdapter implements RateDetails {

    private final Long id;

    private final CurrencyType from;

    private final CurrencyType to;

    private final BigDecimal rate;

    public ImmutableRateDetailsAdapter(final PersistentCurrencyRate rate) {
        this.id = rate.id();
        this.from = rate.getCurrencyFrom();
        this.to = rate.getCurrencyTo();
        this.rate = rate.getRate();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public CurrencyType from() {
        return from;
    }

    @Override
    public CurrencyType to() {
        return to;
    }

    @Override
    public BigDecimal rate() {
        return rate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RateDetails that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(id(), that.id())
                .append(from(), that.from())
                .append(to(), that.to())
                .append(rate(), that.rate())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id())
                .append(from())
                .append(to())
                .append(rate())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id())
                .append("from", from())
                .append("to", to())
                .append("rate", rate())
                .toString();
    }
}