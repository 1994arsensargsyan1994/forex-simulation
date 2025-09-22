package org.arsen.forex.simulation.service.lookup.account;

import org.arsen.forex.simulation.persistence.account.PersistentAccount;

import java.util.Optional;

public interface AccountLookupService  {

    Optional<PersistentAccount> lookup(String id);
}