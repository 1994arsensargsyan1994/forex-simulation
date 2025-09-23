
package org.arsen.forex.service.lookup;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.List;

final class ImmutableAccountResult implements LookupAccountResult {

    private final Integer count;
    private final Collection<AccountDetails> accounts;
    private final Collection<AccountLookupFailure> failures;

    public ImmutableAccountResult(Integer count, Collection<AccountDetails> accounts) {
        this.count = count;
        this.accounts = accounts;
        this.failures = null;
    }

    public ImmutableAccountResult(Collection<AccountLookupFailure> failures) {
        this.count = 0;
        this.accounts = List.of();
        this.failures = failures;
    }

    @Override
    public Collection<AccountLookupFailure> failures() {
        return failures;
    }

    @Override
    public Integer count() {
        return count;
    }

    @Override
    public Collection<AccountDetails> accounts() {
        return accounts;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LookupRateResult that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(count(), that.count())
                .append(accounts(), that.details())
                .append(failures(), that.failures())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(count())
                .append(accounts())
                .append(failures())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("count", count())
                .append("accounts", accounts())
                .append("failures", failures())
                .toString();
    }
}