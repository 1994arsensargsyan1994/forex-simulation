package org.arsen.forex.api.facade.lookup;


import org.arsen.forex.api.model.response.LookupCustomerDetailsResponse;

public interface CustomerLookupHandler {

    LookupCustomerDetailsResponse lookupDetails(Long id);
}