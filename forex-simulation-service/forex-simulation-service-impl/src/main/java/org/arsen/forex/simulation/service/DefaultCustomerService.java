package org.arsen.forex.simulation.service;

import org.arsen.forex.simulation.persistence.customer.CustomerRepository;
import org.arsen.forex.simulation.persistence.customer.PersistentCustomer;
import org.arsen.forex.simulation.service.creation.customer.CustomerCreationFailure;
import org.arsen.forex.simulation.service.creation.customer.CustomerCreationParameters;
import org.arsen.forex.simulation.service.creation.customer.CustomerCreationResult;
import org.arsen.forex.simulation.service.lookup.CustomerLookupService;
import org.arsen.forex.simulation.service.lookup.LookupCustomerDetailsResult;
import org.arsen.forex.simulation.service.lookup.details.ImmutableCustomerDetailsAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerLookupService customerLookupService;

    public DefaultCustomerService(CustomerRepository customerRepository, CustomerLookupService customerLookupService) {
        this.customerRepository = customerRepository;
        this.customerLookupService = customerLookupService;
    }

    @Override
    @Transactional
    public CustomerCreationResult create(CustomerCreationParameters parameters) {
        Assert.notNull(parameters, "parameters must not be null");
        Optional<PersistentCustomer> existingCustomer = customerRepository.findCustomerByEmailOrUsername(parameters.email(), parameters.username());
        if (existingCustomer.isPresent()) {
            return new CustomerCreationResult(
                    List.of(
                            CustomerCreationFailure.COSTUMER_ALREADY_EXISTS_WITH_USERNAME,
                            CustomerCreationFailure.COSTUMER_ALREADY_EXISTS_WITH_EMAIL
                    )
            );
        }
        final PersistentCustomer customer = new PersistentCustomer(
                parameters.username(), parameters.email(), parameters.birthday()
        );
        return new CustomerCreationResult(customerRepository.save(customer).id());
    }

    @Override
    public LookupCustomerDetailsResult lookupDetails(Long id) {
        Assert.notNull(id, "Null was passed as an argument for parameter 'id'.");
        return customerLookupService.lookup(id)
                .map(ImmutableCustomerDetailsAdapter::new)
                .map(LookupCustomerDetailsResult::new)
                .orElseGet(LookupCustomerDetailsResult::notFound);
    }
}
