package org.arsen.forex.service.scheduler;

import org.apache.commons.lang3.time.StopWatch;
import org.arsen.forex.persistence.rate.PersistentCurrencyRate;
import org.arsen.forex.persistence.rate.RateRepository;
import org.arsen.forex.service.utils.RandomRateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
class DefaultRateUpdateSchedulerService implements RateUpdateSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRateUpdateSchedulerService.class);

    private final RateRepository rateRepository;

    DefaultRateUpdateSchedulerService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Scheduled(fixedDelayString = "${rate.update.job.cron.expression:10}", timeUnit = TimeUnit.SECONDS)
    @Override
    public void execute() {
        logger.debug("Rate Update job was started...");

        final StopWatch stopWatch = StopWatch.createStarted();

        List<PersistentCurrencyRate> rates = rateRepository.findAll();

        for (PersistentCurrencyRate rate : rates) {
            var newRate = RandomRateGenerator.generate(rate);
            rate.setRate(newRate);
            rate.setLastUpdated(LocalDateTime.now());
        }

        logger.info(
                "Rate Update job was successfully executed in {} millis.",
                stopWatch.getTime()
        );
    }
}
