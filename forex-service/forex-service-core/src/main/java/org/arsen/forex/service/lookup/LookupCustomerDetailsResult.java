package org.arsen.forex.service.lookup;


import org.arsen.forex.common.Result;

import java.util.Collection;
import java.util.List;

public class LookupCustomerDetailsResult implements Result<LookupCustomerDetailsFailure> {

    private final CustomerDetails details;
    private final Collection<LookupCustomerDetailsFailure> failures;

    public LookupCustomerDetailsResult(final CustomerDetails details) {
        this.details = details;
        this.failures = null;
    }

    private LookupCustomerDetailsResult(final Collection<LookupCustomerDetailsFailure> failures) {
        this.details = null;
        this.failures = failures;
    }

    public static LookupCustomerDetailsResult notFound() {
        return new LookupCustomerDetailsResult(
                List.of(LookupCustomerDetailsFailure.URM_LIST_NOT_FOUND)
        );
    }

    public CustomerDetails getDetails() {
        return details;
    }

    @Override
    public Collection<LookupCustomerDetailsFailure> failures() {
        return failures;
    }
}
