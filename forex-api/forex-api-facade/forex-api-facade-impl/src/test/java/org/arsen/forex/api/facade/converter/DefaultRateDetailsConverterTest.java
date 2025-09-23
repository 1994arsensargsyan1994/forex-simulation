package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.facade.AbstractForexServiceFacadeTest;
import org.arsen.forex.api.model.response.details.RatesDetailsDto;
import org.arsen.forex.service.lookup.RateDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.arsen.forex.api.facade.ForexServiceFacadeTestHelper.rateDetails;

public class DefaultRateDetailsConverterTest extends AbstractForexServiceFacadeTest {

    @InjectMocks
    private DefaultRateDetailsConverter rateDetailsConverter;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
    }

    @Test
    @SuppressWarnings("all")
    public void testConvertWithInvalidParameter() {
        Assertions.assertThatThrownBy(() -> rateDetailsConverter.convert(null))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> rateDetailsConverter.convertAll(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvert() {
        final RateDetails source = rateDetails();
        assertEquals(rateDetailsConverter.convert(source), source);
    }

    @Test
    public void testConvertAll() {
        final RateDetails first = rateDetails();
        final RateDetails second = rateDetails();

        final List<RateDetails> listDetails = List.of(first, second);

        final Collection<RatesDetailsDto> convertedDetails = rateDetailsConverter.convertAll(listDetails);

        Assertions.assertThat(convertedDetails).isNotEmpty();
        Assertions.assertThat(convertedDetails.size()).isEqualTo(listDetails.size());

        final Iterator<RatesDetailsDto> iterator = convertedDetails.iterator();
        assertEquals(iterator.next(), first);
        assertEquals(iterator.next(), second);
    }

    private static void assertEquals(final RatesDetailsDto listDetails, final RateDetails source) {
        Assertions.assertThat(listDetails).isNotNull();
        Assertions.assertThat(listDetails.getId()).isEqualTo(source.id());
        Assertions.assertThat(listDetails.getRate()).isEqualTo(source.rate());

        Assertions.assertThat(listDetails.getFrom()).isEqualTo(source.from());
        Assertions.assertThat(listDetails.getTo()).isEqualTo(source.to());
    }
}