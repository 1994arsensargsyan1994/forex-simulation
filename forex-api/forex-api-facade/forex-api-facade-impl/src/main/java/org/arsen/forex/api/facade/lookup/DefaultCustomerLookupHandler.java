package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.facade.converter.CustomerDetailsConverter;
import org.arsen.forex.api.model.response.LookupCustomerDetailsResponse;
import org.arsen.forex.service.CustomerService;
import org.arsen.forex.service.lookup.LookupCustomerDetailsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultCustomerLookupHandler implements CustomerLookupHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCustomerLookupHandler.class);

    private final CustomerService customerService;
    private final CustomerDetailsConverter detailsConverter;

    public DefaultCustomerLookupHandler(CustomerService customerService, CustomerDetailsConverter detailsConverter) {
        this.customerService = customerService;
        this.detailsConverter = detailsConverter;
    }

    @Override
    public LookupCustomerDetailsResponse lookupDetails(Long id) {
        final LookupCustomerDetailsResult lookupDetailsResult = customerService.lookupDetails(id);
        final LookupCustomerDetailsResponse response = new LookupCustomerDetailsResponse();

        if (lookupDetailsResult.hasFailures()) {
            logger.warn("Lookup URM list details failed with: {}.", lookupDetailsResult.failures());
            response.setResultFailures(lookupDetailsResult.failures());
            return response;
        }
        response.setDetails(detailsConverter.convert(lookupDetailsResult.getDetails()));
        return response;
    }
}