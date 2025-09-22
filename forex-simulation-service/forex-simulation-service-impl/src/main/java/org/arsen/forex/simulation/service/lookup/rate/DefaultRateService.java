package org.arsen.forex.simulation.service.lookup.rate;

import org.arsen.forex.simulation.persistence.rate.RateRepository;
import org.arsen.forex.simulation.service.RateService;
import org.arsen.forex.simulation.service.lookup.LookupRateResult;
import org.arsen.forex.simulation.service.lookup.RateDetails;
import org.arsen.forex.simulation.service.lookup.details.ImmutableRateDetailsAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class DefaultRateService implements RateService {

    private final RateRepository rateRepository;

    DefaultRateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public LookupRateResult lookup() {

        final List<RateDetails> list = rateRepository.findAll()
                .stream()
                .map(ImmutableRateDetailsAdapter::new)
                .collect(Collectors.toList());

        return LookupRateResult.of(list.size(), list);
    }
}