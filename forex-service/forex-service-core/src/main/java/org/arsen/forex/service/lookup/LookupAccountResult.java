package org.arsen.forex.service.lookup;

import org.arsen.forex.common.Result;

import java.util.Collection;

public interface LookupAccountResult extends Result<AccountLookupFailure> {

    Integer count();

    Collection<AccountDetails> accounts();

    static LookupAccountResult of(final int count, final Collection<AccountDetails> accounts) {
        if (count < 0) {
            throw new IllegalArgumentException("Null or negative number was passed as an argument for parameter 'count'.");
        }
        if (accounts == null) {
            throw new IllegalArgumentException("Null was passed as an argument for parameter 'details'.");
        }
        return new ImmutableAccountResult(count, accounts);
    }

    static LookupAccountResult of(final Collection<AccountLookupFailure> failures) {
        if (failures == null || failures.isEmpty()) {
            throw new IllegalArgumentException("Null or empty list was passed as an argument for parameter 'failures'.");
        }
        return new ImmutableAccountResult(failures);
    }
}