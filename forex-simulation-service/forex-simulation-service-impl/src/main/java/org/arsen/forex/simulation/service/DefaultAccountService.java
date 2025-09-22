package org.arsen.forex.simulation.service;

import org.arsen.forex.simulation.persistence.account.AccountRepository;
import org.arsen.forex.simulation.persistence.account.PersistentAccount;
import org.arsen.forex.simulation.persistence.customer.CustomerRepository;
import org.arsen.forex.simulation.persistence.customer.PersistentCustomer;
import org.arsen.forex.simulation.service.creation.account.AccountCreationFailure;
import org.arsen.forex.simulation.service.creation.account.AccountCreationParameters;
import org.arsen.forex.simulation.service.creation.account.AccountCreationResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public DefaultAccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public AccountCreationResult create(AccountCreationParameters parameters) {

        final Optional<PersistentCustomer> customerOpt = customerRepository.findById(parameters.customerId());

        if (customerOpt.isEmpty()) {
            return new AccountCreationResult(
                    List.of(AccountCreationFailure.CUSTOMER_NOT_FOUND)
            );
        }

        final PersistentCustomer customer = customerOpt.get();
        final Optional<PersistentAccount> existingAccount = accountRepository.findByCurrencyAndCustomer(parameters.currency(), customer);

        if (existingAccount.isPresent()) {
            return new AccountCreationResult(
                    List.of(AccountCreationFailure.ACCOUNT_ALREADY_EXISTS_FOR_CURRENCY_TYPE)
            );
        }

        final PersistentAccount account = new PersistentAccount(
                UUID.randomUUID().toString(),
                parameters.currency(),
                parameters.balance(),
                customer
        );
        return new AccountCreationResult(accountRepository.save(account).getNumber());
    }
}
