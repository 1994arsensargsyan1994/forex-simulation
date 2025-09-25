package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.model.response.LookupOrderDetailsResponse;

public interface OrderLookupHandler {

    LookupOrderDetailsResponse lookupDetails(Long id);
}