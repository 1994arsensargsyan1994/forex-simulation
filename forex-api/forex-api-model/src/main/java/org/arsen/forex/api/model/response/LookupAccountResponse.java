package org.arsen.forex.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.api.model.response.details.AccountDetailsDto;
import org.arsen.forex.common.api.model.FailureAwareResponse;

import java.util.Collection;

public class LookupAccountResponse extends FailureAwareResponse {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("accounts")
    private Collection<AccountDetailsDto> accounts;

    public LookupAccountResponse() {
        super();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    public Collection<AccountDetailsDto> getAccounts() {
        return accounts;
    }

    public void setItems(final Collection<AccountDetailsDto> rates) {
        this.accounts = rates;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LookupAccountResponse that)) {
            return false;
        }
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getCount(), that.getCount())
                .append(getAccounts(), that.getAccounts())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getCount())
                .append(getAccounts())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("count", getCount())
                .append("rates", getAccounts())
                .toString();
    }
}
