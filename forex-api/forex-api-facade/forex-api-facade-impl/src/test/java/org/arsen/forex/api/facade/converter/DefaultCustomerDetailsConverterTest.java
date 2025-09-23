package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.facade.AbstractForexServiceFacadeTest;
import org.arsen.forex.api.model.response.details.CustomerDetailsDto;
import org.arsen.forex.service.lookup.CustomerDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.arsen.forex.api.facade.ForexServiceFacadeTestHelper.customerDetails;

public class DefaultCustomerDetailsConverterTest extends AbstractForexServiceFacadeTest {

    @InjectMocks
    private DefaultCustomerDetailsConverter detailsConverter;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
    }

    @Test
    @SuppressWarnings("all")
    public void testConvertWithInvalidParameter() {
        Assertions.assertThatThrownBy(() -> detailsConverter.convert(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvert() {
        final var source = customerDetails();

        assertEquals(detailsConverter.convert(source), source);
    }

    private static void assertEquals(final CustomerDetailsDto listDetails, final CustomerDetails source) {
        Assertions.assertThat(listDetails).isNotNull();
        Assertions.assertThat(listDetails.getId()).isEqualTo(source.id());
        Assertions.assertThat(listDetails.getUsername()).isEqualTo(source.username());
        Assertions.assertThat(listDetails.getEmail()).isEqualTo(source.email());
        Assertions.assertThat(listDetails.getDateOfBirth()).isEqualTo(source.dateOfBirth());
    }
}