package org.arsen.forex.simulation.service.creation.customer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.common.Result;

import java.util.Collection;

public class CustomerCreationResult implements Result<CustomerCreationFailure> {

    private final Long id;
    private final Collection<CustomerCreationFailure> failures;

    public CustomerCreationResult(final Long id) {
        this.id = id;
        this.failures = null;
    }

    public CustomerCreationResult(final Collection<CustomerCreationFailure> failures) {
        this.id = null;
        this.failures = failures;
    }

    @Override
    public Collection<CustomerCreationFailure> failures() {
        return failures;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CustomerCreationResult that)) {
            return false;
        }

        return new EqualsBuilder()
                .append(id, that.id)
                .append(failures, that.failures)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(failures)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("failures", failures)
                .toString();
    }
}
