package org.arsen.forex.service.lookup.account;

import org.arsen.forex.persistence.account.AccountRepository;
import org.arsen.forex.persistence.account.PersistentAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class DefaultAccountLookupService implements AccountLookupService {

    private final AccountRepository accountRepository;

    public DefaultAccountLookupService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersistentAccount> lookup(String id) {
        return accountRepository.findAccountByNumber(id);
    }
}