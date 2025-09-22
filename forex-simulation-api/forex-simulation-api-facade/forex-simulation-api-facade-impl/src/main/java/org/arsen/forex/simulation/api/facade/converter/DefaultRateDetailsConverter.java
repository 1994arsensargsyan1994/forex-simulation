
package org.arsen.forex.simulation.api.facade.converter;

import org.arsen.forex.simulation.api.model.response.details.RatesDetailsDto;
import org.arsen.forex.simulation.service.lookup.RateDetails;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultRateDetailsConverter implements RateDetailsConverter {

    @Override
    public RatesDetailsDto convert(@NonNull final RateDetails source) {
        Assert.notNull(source, "Null was passed as an argument for parameter 'source'.");
        final RatesDetailsDto listDetails = new RatesDetailsDto();
        listDetails.setId(source.id());
        listDetails.setFrom(source.from());
        listDetails.setTo(source.to());
        listDetails.setRate(source.rate());
        return listDetails;
    }
}