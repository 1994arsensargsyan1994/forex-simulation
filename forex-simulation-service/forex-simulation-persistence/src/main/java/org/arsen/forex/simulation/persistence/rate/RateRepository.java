package org.arsen.forex.simulation.persistence.rate;

import org.arsen.forex.simulation.common.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<PersistentCurrencyRate, Long> {

    PersistentCurrencyRate findByCurrencyFromAndCurrencyTo(CurrencyType from, CurrencyType to);
}