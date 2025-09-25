package org.arsen.forex.service.account;

import org.arsen.forex.persistence.account.AccountRepository;
import org.arsen.forex.persistence.account.PersistentAccount;
import org.arsen.forex.persistence.customer.CustomerRepository;
import org.arsen.forex.persistence.customer.PersistentCustomer;
import org.arsen.forex.service.AccountService;
import org.arsen.forex.service.creation.account.AccountCreationFailure;
import org.arsen.forex.service.creation.account.AccountCreationParameters;
import org.arsen.forex.service.creation.account.AccountCreationResult;
import org.arsen.forex.service.lookup.AccountDetails;
import org.arsen.forex.service.lookup.AccountLookupFailure;
import org.arsen.forex.service.lookup.LookupAccountResult;
import org.arsen.forex.service.lookup.details.ImmutableAccountDetailsAdapter;
import org.arsen.forex.service.utils.AccountNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                AccountNumberGenerator.generate(),
                parameters.currency(),
                parameters.balance(),
                customer
        );
        return new AccountCreationResult(accountRepository.save(account).getNumber());
    }

    @Override
    public LookupAccountResult lookupAll(Long customerId) {


        final Optional<PersistentCustomer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isEmpty()) {
            return LookupAccountResult.of(
                    List.of(AccountLookupFailure.CUSTOMER_NOT_FOUND)
            );
        }

        final PersistentCustomer customer = customerOpt.get();
        final Collection<PersistentAccount> accounts = accountRepository.findAllByCustomer(customer);

        final List<AccountDetails> list = accountRepository.findAllByCustomer(customer)
                .stream()
                .map(ImmutableAccountDetailsAdapter::new)
                .collect(Collectors.toList());

        return LookupAccountResult.of(list.size(), list);
    }

}
