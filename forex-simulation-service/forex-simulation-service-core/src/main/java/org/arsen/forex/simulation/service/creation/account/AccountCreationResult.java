package org.arsen.forex.simulation.service.creation.account;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.common.Result;

import java.util.Collection;

public class AccountCreationResult implements Result<AccountCreationFailure> {

    private final String number;
    private final Collection<AccountCreationFailure> failures;

    @Override
    public Collection<AccountCreationFailure> failures() {
        return failures;
    }

    public AccountCreationResult(String number) {
        this.number = number;
        this.failures = null;
    }

    public AccountCreationResult(Collection<AccountCreationFailure> failures) {
        this.failures = failures;
        this.number = null;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AccountCreationResult that)) {
            return false;
        }

        return new EqualsBuilder()
                .append(number, that.number)
                .append(failures, that.failures)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(number)
                .append(failures)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("failures", failures)
                .toString();
    }
}
