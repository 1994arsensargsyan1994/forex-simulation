package org.arsen.forex.service.lookup.rate;

import org.arsen.forex.persistence.rate.RateRepository;
import org.arsen.forex.service.RateService;
import org.arsen.forex.service.lookup.LookupRateResult;
import org.arsen.forex.service.lookup.RateDetails;
import org.arsen.forex.service.lookup.details.ImmutableRateDetailsAdapter;
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