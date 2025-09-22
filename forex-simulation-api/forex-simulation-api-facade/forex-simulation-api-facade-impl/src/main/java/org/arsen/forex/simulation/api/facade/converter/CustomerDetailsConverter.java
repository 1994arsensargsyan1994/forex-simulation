package org.arsen.forex.simulation.api.facade.converter;

import org.arsen.forex.simulation.api.model.response.details.CustomerDetailsDto;
import org.arsen.forex.simulation.service.lookup.CustomerDetails;
import org.springframework.core.convert.converter.Converter;

public interface CustomerDetailsConverter extends Converter<CustomerDetails, CustomerDetailsDto> {
}