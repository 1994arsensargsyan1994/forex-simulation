package org.arsen.forex.api.facade.converter;

import jakarta.validation.constraints.NotNull;
import org.arsen.forex.api.model.response.details.OrderDetailsDto;
import org.arsen.forex.service.lookup.OrderDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultOrderDetailsConverter implements OrderDetailsConverter {

    @Override
    public OrderDetailsDto convert(@NotNull OrderDetails source) {
        Assert.notNull(source, "Null was passed as an argument for parameter 'source'.");
        OrderDetailsDto customerDetailsDto = new OrderDetailsDto();
        customerDetailsDto.setCurrencyFrom(source.currencyFrom());
        customerDetailsDto.setCurrencyTo(source.currencyTo());
        customerDetailsDto.setAmount(source.amount());
        customerDetailsDto.setRate(source.rate());
        customerDetailsDto.setStatus(source.status());
        customerDetailsDto.setFailedReason(source.failedReason());
        return customerDetailsDto;
    }
}
