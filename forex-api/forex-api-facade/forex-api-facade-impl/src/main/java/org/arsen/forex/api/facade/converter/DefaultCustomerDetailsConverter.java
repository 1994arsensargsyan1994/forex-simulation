package org.arsen.forex.api.facade.converter;

import jakarta.validation.constraints.NotNull;
import org.arsen.forex.api.model.response.details.CustomerDetailsDto;
import org.arsen.forex.service.lookup.CustomerDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultCustomerDetailsConverter implements CustomerDetailsConverter {

    @Override
    public CustomerDetailsDto convert(@NotNull CustomerDetails source) {
        Assert.notNull(source, "Null was passed as an argument for parameter 'source'.");
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        customerDetailsDto.setId(source.id());
        customerDetailsDto.setEmail(source.email());
        customerDetailsDto.setUsername(source.username());
        customerDetailsDto.setDateOfBirth(source.dateOfBirth());
        return customerDetailsDto;
    }
}
