
package org.arsen.forex.service.lookup;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.common.Failure;

import java.util.Collection;
import java.util.List;

record ImmutableRateResult(Integer count, Collection<RateDetails> details) implements LookupRateResult {

    @Override
    public Collection<Failure> failures() {
        return List.of();
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
                .append(details(), that.details())
                .append(failures(), that.failures())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(count())
                .append(details())
                .append(failures())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("count", count())
                .append("details", details())
                .append("failures", failures())
                .toString();
    }
}