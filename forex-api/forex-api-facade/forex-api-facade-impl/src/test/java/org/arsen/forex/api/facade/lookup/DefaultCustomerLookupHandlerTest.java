package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.facade.AbstractForexServiceFacadeTest;
import org.arsen.forex.api.facade.converter.CustomerDetailsConverter;
import org.arsen.forex.api.model.response.LookupCustomerDetailsResponse;
import org.arsen.forex.service.CustomerService;
import org.arsen.forex.service.lookup.LookupCustomerDetailsFailure;
import org.arsen.forex.service.lookup.LookupCustomerDetailsResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.arsen.forex.api.facade.ForexServiceFacadeTestHelper.customerDetails;
import static org.arsen.forex.api.facade.ForexServiceFacadeTestHelper.customerDetailsDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DefaultCustomerLookupHandlerTest extends AbstractForexServiceFacadeTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerDetailsConverter detailsConverter;

    @InjectMocks
    private DefaultCustomerLookupHandler customerLookupHandler;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(customerService, detailsConverter);
    }

    @Test
    void testLookupDetailsWhenServiceReturnsFailures() {
        final Long id = randomId();

        var failedResult = mock(LookupCustomerDetailsResult.class);
        when(failedResult.hasFailures()).thenReturn(true);
        when(failedResult.failures()).thenReturn(List.of(LookupCustomerDetailsFailure.CUSTOMER_NOT_FOUND));
        when(customerService.lookupDetails(id)).thenReturn(failedResult);

        var resp = customerLookupHandler.lookupDetails(id);

        assertThat(resp).isNotNull();
        assertThat(resp.isFailed()).isTrue();

        verify(customerService).lookupDetails(id);
        verify(detailsConverter, never()).convert(any());
    }

    @Test
    void testLookupDetailsWhenServiceReturnsDetailsSuccessful() {
        final Long id = randomId();

        var details = customerDetails();
        var dtoDetails = customerDetailsDto();

        var okResult = mock(LookupCustomerDetailsResult.class);
        when(okResult.hasFailures()).thenReturn(false);
        when(okResult.getDetails()).thenReturn(details);

        when(customerService.lookupDetails(id)).thenReturn(okResult);
        when(detailsConverter.convert(details)).thenReturn(dtoDetails);

        LookupCustomerDetailsResponse resp = customerLookupHandler.lookupDetails(id);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        assertThat(resp.getDetails()).isSameAs(dtoDetails);

        verify(customerService).lookupDetails(id);
        verify(detailsConverter).convert(details);
    }

}
