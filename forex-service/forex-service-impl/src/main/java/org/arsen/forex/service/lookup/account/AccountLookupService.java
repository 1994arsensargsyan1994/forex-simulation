package org.arsen.forex.service.lookup.account;

import org.arsen.forex.persistence.account.PersistentAccount;

import java.util.Optional;

public interface AccountLookupService  {

    Optional<PersistentAccount> lookup(String id);
}