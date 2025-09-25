package org.arsen.forex.service.lookup.details;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.common.CurrencyType;
import org.arsen.forex.common.OrderStatus;
import org.arsen.forex.persistence.order.PersistentOrder;
import org.arsen.forex.service.lookup.OrderDetails;

import java.math.BigDecimal;

public final class ImmutableOrderDetailsAdapter implements OrderDetails {

    private final CurrencyType currencyFrom;
    private final CurrencyType currencyTo;
    private final BigDecimal amount;
    private final BigDecimal rate;
    private final OrderStatus status;
    private final String failedReason;

    public ImmutableOrderDetailsAdapter(final PersistentOrder order) {
        this.currencyFrom = order.getAccountFrom().getCurrency();
        this.currencyTo = order.getAccountTo().getCurrency();
        this.amount = order.getAmount();
        this.rate = order.getRate();
        this.status = order.getStatus();
        this.failedReason = order.getFailedReason();
    }

    @Override
    public CurrencyType currencyFrom() {
        return currencyFrom;
    }

    @Override
    public CurrencyType currencyTo() {
        return currencyTo;
    }

    @Override
    public BigDecimal amount() {
        return amount;
    }

    @Override
    public BigDecimal rate() {
        return rate;
    }

    @Override
    public OrderStatus status() {
        return status;
    }

    @Override
    public String failedReason() {
        return failedReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ImmutableOrderDetailsAdapter that)) return false;

        return new EqualsBuilder()
                .append(currencyFrom, that.currencyFrom)
                .append(currencyTo, that.currencyTo)
                .append(amount, that.amount)
                .append(rate, that.rate)
                .append(status, that.status)
                .append(failedReason, that.failedReason)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(currencyFrom)
                .append(currencyTo)
                .append(amount)
                .append(rate)
                .append(status)
                .append(failedReason)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("currencyFrom", currencyFrom)
                .append("currencyTo", currencyTo)
                .append("amount", amount)
                .append("rate", rate)
                .append("status", status)
                .append("failedReason", failedReason)
                .toString();
    }
}