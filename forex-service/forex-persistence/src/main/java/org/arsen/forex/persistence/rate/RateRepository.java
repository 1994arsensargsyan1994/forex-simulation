package org.arsen.forex.persistence.rate;

import org.arsen.forex.common.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<PersistentCurrencyRate, Long> {

    PersistentCurrencyRate findByCurrencyFromAndCurrencyTo(CurrencyType from, CurrencyType to);
}