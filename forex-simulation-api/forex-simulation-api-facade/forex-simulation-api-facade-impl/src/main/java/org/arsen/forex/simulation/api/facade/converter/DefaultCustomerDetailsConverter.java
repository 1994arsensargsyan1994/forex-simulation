package org.arsen.forex.simulation.api.facade.converter;

import jakarta.validation.constraints.NotNull;
import org.arsen.forex.simulation.api.model.response.details.CustomerDetailsDto;
import org.arsen.forex.simulation.service.lookup.CustomerDetails;
import org.springframework.stereotype.Component;

@Component
class DefaultCustomerDetailsConverter implements CustomerDetailsConverter {

    @Override
    public CustomerDetailsDto convert(@NotNull CustomerDetails source) {
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        customerDetailsDto.setId(source.id());
        customerDetailsDto.setEmail(source.email());
        customerDetailsDto.setUsername(source.username());
        customerDetailsDto.setDateOfBirth(source.dateOfBirth());
        return customerDetailsDto;
    }
}
