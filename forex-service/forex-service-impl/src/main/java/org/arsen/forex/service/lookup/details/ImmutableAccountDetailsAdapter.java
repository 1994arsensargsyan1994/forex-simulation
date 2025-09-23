package org.arsen.forex.service.lookup.details;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.common.CurrencyType;
import org.arsen.forex.persistence.account.PersistentAccount;
import org.arsen.forex.service.lookup.AccountDetails;

import java.math.BigDecimal;

public final class ImmutableAccountDetailsAdapter implements AccountDetails {

    private final String number;

    private final CurrencyType currency;

    private final BigDecimal balance;

    private final boolean isDisabled;

    public ImmutableAccountDetailsAdapter(final PersistentAccount account) {
        this.number = account.getNumber();
        this.currency = account.getCurrency();
        this.balance = account.getBalance();
        this.isDisabled = account.isDisabled();
    }

    @Override
    public String number() {
        return number;
    }

    @Override
    public CurrencyType currency() {
        return currency;
    }

    @Override
    public BigDecimal balance() {
        return balance;
    }

    @Override
    public boolean isDisabled() {
        return isDisabled;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountDetails that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(number(), that.number())
                .append(currency(), that.currency())
                .append(balance(), that.balance())
                .append(isDisabled(), that.isDisabled())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(number())
                .append(currency())
                .append(balance())
                .append(isDisabled())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number())
                .append("currency", currency())
                .append("currency", balance())
                .append("isDisabled", isDisabled())
                .toString();
    }
}