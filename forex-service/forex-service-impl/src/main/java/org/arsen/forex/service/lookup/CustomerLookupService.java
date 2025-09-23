package org.arsen.forex.service.lookup;

import org.arsen.forex.common.exception.MissingCustomerException;
import org.arsen.forex.persistence.customer.PersistentCustomer;

import java.util.Optional;

public interface CustomerLookupService {

    Optional<PersistentCustomer> lookup(Long customerId);

    default PersistentCustomer get(final Long id) {
        return lookup(id).orElseThrow(() ->
                new MissingCustomerException(
                        String.format("Customer not found. Id: %s.", id)
                )
        );
    }
}
