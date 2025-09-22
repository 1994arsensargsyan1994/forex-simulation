package org.arsen.forex.simulation.service.lookup.details;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.simulation.persistence.customer.PersistentCustomer;
import org.arsen.forex.simulation.service.lookup.CustomerDetails;

import java.time.LocalDate;

public final class ImmutableCustomerDetailsAdapter implements CustomerDetails {

    private final Long id;

    private final String username;

    private final String email;

    private final LocalDate dateOfBirth;

    public ImmutableCustomerDetailsAdapter(final PersistentCustomer customer) {
        this.id = customer.id();
        this.username = customer.getUsername();
        this.email = customer.getEmail();
        this.dateOfBirth = customer.getDateOfBirth();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final CustomerDetails that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(id(), that.id())
                .append(username(), that.username())
                .append(email(), that.email())
                .append(dateOfBirth(), that.dateOfBirth())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id())
                .append(username())
                .append(email())
                .append(dateOfBirth())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id())
                .append("username", username())
                .append("email", email())
                .append("dateOfBirth", dateOfBirth())
                .toString();
    }
}