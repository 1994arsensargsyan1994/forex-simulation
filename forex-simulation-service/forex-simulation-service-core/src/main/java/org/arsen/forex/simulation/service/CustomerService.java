package org.arsen.forex.simulation.service;

import org.arsen.forex.simulation.service.creation.customer.CustomerCreationParameters;
import org.arsen.forex.simulation.service.creation.customer.CustomerCreationResult;
import org.arsen.forex.simulation.service.lookup.LookupCustomerDetailsResult;

public interface CustomerService {

    CustomerCreationResult create(CustomerCreationParameters parameters);

    LookupCustomerDetailsResult lookupDetails(Long id);
}
