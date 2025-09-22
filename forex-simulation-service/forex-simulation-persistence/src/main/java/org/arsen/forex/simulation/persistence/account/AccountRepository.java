package org.arsen.forex.simulation.persistence.account;

import org.arsen.forex.simulation.common.AccountType;
import org.arsen.forex.simulation.persistence.customer.PersistentCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<PersistentAccount, Long> {

    Optional<PersistentAccount> findAccountByTypeAndCustomer(AccountType type, PersistentCustomer customer);

    Optional<PersistentAccount> findAccountByNumber(String number);
}
