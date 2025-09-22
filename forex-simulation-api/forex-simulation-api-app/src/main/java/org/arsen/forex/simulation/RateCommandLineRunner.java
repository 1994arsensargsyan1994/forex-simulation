package org.arsen.forex.simulation;

import org.arsen.forex.simulation.common.CurrencyType;
import org.arsen.forex.simulation.persistence.rate.PersistentCurrencyRate;
import org.arsen.forex.simulation.persistence.rate.RateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RateCommandLineRunner implements CommandLineRunner {

    private final RateRepository rateRepository;

    public RateCommandLineRunner(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public void run(String... args) {
        if (rateRepository.count() > 0) {
            return; // already seeded
        }

        List<PersistentCurrencyRate> rates = List.of(
                new PersistentCurrencyRate(CurrencyType.USD, CurrencyType.AMD, BigDecimal.valueOf(390.00), LocalDateTime.now()),
                new PersistentCurrencyRate(CurrencyType.AMD, CurrencyType.USD, BigDecimal.valueOf(0.0026), LocalDateTime.now()),

                new PersistentCurrencyRate(CurrencyType.USD, CurrencyType.RUB, BigDecimal.valueOf(95.00), LocalDateTime.now()),
                new PersistentCurrencyRate(CurrencyType.RUB, CurrencyType.USD, BigDecimal.valueOf(0.0105), LocalDateTime.now()),

                new PersistentCurrencyRate(CurrencyType.USD, CurrencyType.GBP, BigDecimal.valueOf(0.78), LocalDateTime.now()),
                new PersistentCurrencyRate(CurrencyType.GBP, CurrencyType.USD, BigDecimal.valueOf(1.28), LocalDateTime.now()),

                new PersistentCurrencyRate(CurrencyType.USD, CurrencyType.JPY, BigDecimal.valueOf(149.00), LocalDateTime.now()),
                new PersistentCurrencyRate(CurrencyType.JPY, CurrencyType.USD, BigDecimal.valueOf(0.0067), LocalDateTime.now())
        );

        rateRepository.saveAll(rates);
    }
}
