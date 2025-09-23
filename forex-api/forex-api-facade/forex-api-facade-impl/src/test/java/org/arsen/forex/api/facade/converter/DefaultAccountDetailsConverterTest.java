package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.facade.AbstractForexServiceFacadeTest;
import org.arsen.forex.api.model.response.details.AccountDetailsDto;
import org.arsen.forex.service.lookup.AccountDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.arsen.forex.api.facade.ForexServiceFacadeTestHelper.accountDetails;

public class DefaultAccountDetailsConverterTest extends AbstractForexServiceFacadeTest {

    @InjectMocks
    private DefaultAccountDetailsConverter accountDetailsConverter;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
    }

    @Test
    @SuppressWarnings("all")
    public void testConvertWithInvalidParameter() {
        Assertions.assertThatThrownBy(() -> accountDetailsConverter.convert(null))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> accountDetailsConverter.convertAll(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testConvert() {
        final AccountDetails source = accountDetails();
        assertEquals(accountDetailsConverter.convert(source), source);
    }

    @Test
    public void testConvertAll() {
        final AccountDetails first = accountDetails();
        final AccountDetails second = accountDetails();

        final List<AccountDetails> listDetails = List.of(first, second);

        final Collection<AccountDetailsDto> convertedDetails = accountDetailsConverter.convertAll(listDetails);

        Assertions.assertThat(convertedDetails).isNotEmpty();
        Assertions.assertThat(convertedDetails.size()).isEqualTo(listDetails.size());

        final Iterator<AccountDetailsDto> iterator = convertedDetails.iterator();
        assertEquals(iterator.next(), first);
        assertEquals(iterator.next(), second);
    }

    private static void assertEquals(final AccountDetailsDto listDetails, final AccountDetails source) {
        Assertions.assertThat(listDetails).isNotNull();
        Assertions.assertThat(listDetails.getBalance()).isEqualTo(source.balance());
        Assertions.assertThat(listDetails.getCurrency()).isEqualTo(source.currency());
        Assertions.assertThat(listDetails.getNumber()).isEqualTo(source.number());
    }
}