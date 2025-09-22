package org.arsen.forex.simulation.service.lookup;

import org.arsen.forex.simulation.persistence.customer.CustomerRepository;
import org.arsen.forex.simulation.persistence.customer.PersistentCustomer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
class DefaultCustomerLookupService implements CustomerLookupService {

    private final CustomerRepository customerRepository;

    public DefaultCustomerLookupService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersistentCustomer> lookup(Long customerId) {
        return customerRepository.findById(customerId);
    }
}
