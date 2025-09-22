package org.arsen.forex.simulation.api.facade.lookup;


import org.arsen.forex.simulation.api.model.response.LookupCustomerDetailsResponse;

public interface CustomerLookupHandler {

    LookupCustomerDetailsResponse lookupDetails(Long id);
}