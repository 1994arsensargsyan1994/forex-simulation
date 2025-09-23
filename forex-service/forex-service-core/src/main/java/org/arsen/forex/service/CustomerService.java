package org.arsen.forex.service;

import org.arsen.forex.service.creation.customer.CustomerCreationParameters;
import org.arsen.forex.service.creation.customer.CustomerCreationResult;
import org.arsen.forex.service.lookup.LookupCustomerDetailsResult;

public interface CustomerService {

    CustomerCreationResult create(CustomerCreationParameters parameters);

    LookupCustomerDetailsResult lookupDetails(Long id);
}
