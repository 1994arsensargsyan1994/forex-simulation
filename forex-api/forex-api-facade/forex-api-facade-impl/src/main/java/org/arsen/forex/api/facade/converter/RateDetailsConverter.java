package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.model.response.details.RatesDetailsDto;
import org.arsen.forex.service.lookup.RateDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public interface RateDetailsConverter extends Converter<RateDetails, RatesDetailsDto> {

    default Collection<RatesDetailsDto> convertAll(final Collection<RateDetails> details) {
        Assert.notNull(details, "Null was passed as an argument for parameter 'details'.");
        return details.stream().map(this::convert).filter(Objects::nonNull).collect(Collectors.toUnmodifiableList());
    }
}