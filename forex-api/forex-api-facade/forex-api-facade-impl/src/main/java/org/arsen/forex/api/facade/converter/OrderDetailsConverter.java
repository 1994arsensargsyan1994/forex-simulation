package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.model.response.details.OrderDetailsDto;
import org.arsen.forex.service.lookup.OrderDetails;
import org.springframework.core.convert.converter.Converter;

public interface OrderDetailsConverter extends Converter<OrderDetails, OrderDetailsDto> {
}