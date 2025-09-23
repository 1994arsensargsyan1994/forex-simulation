package org.arsen.forex.persistence.account;

import org.arsen.forex.common.CurrencyType;
import org.arsen.forex.persistence.customer.PersistentCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<PersistentAccount, Long> {

    Optional<PersistentAccount> findByCurrencyAndCustomer(CurrencyType type, PersistentCustomer customer);

    Optional<PersistentAccount> findAccountByNumber(String number);

    Collection<PersistentAccount> findAllByCustomer(PersistentCustomer customer);
}
