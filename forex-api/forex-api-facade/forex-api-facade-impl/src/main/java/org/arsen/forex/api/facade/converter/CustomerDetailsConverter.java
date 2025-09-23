package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.model.response.details.CustomerDetailsDto;
import org.arsen.forex.service.lookup.CustomerDetails;
import org.springframework.core.convert.converter.Converter;

public interface CustomerDetailsConverter extends Converter<CustomerDetails, CustomerDetailsDto> {
}