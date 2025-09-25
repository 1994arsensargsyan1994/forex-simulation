package org.arsen.forex.service.lookup;

import org.arsen.forex.common.Result;

import java.util.Collection;
import java.util.List;

public class LookupOrderDetailsResult implements Result<LookupOrderDetailsFailure> {

    private final OrderDetails details;
    private final Collection<LookupOrderDetailsFailure> failures;

    public LookupOrderDetailsResult(final OrderDetails details) {
        this.details = details;
        this.failures = null;
    }

    private LookupOrderDetailsResult(final Collection<LookupOrderDetailsFailure> failures) {
        this.details = null;
        this.failures = failures;
    }

    public static LookupOrderDetailsResult notFound() {
        return new LookupOrderDetailsResult(
                List.of(LookupOrderDetailsFailure.ORDER_NOT_FOUND)
        );
    }

    public OrderDetails getDetails() {
        return details;
    }

    @Override
    public Collection<LookupOrderDetailsFailure> failures() {
        return failures;
    }
}