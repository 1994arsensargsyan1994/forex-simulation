package org.arsen.forex.api.facade;

import org.apache.commons.lang3.RandomUtils;
import org.arsen.forex.api.model.request.AccountCreationRequest;
import org.arsen.forex.api.model.request.CustomerCreationRequest;
import org.arsen.forex.api.model.response.details.CustomerDetailsDto;
import org.arsen.forex.common.CurrencyType;
import org.arsen.forex.service.lookup.AccountDetails;
import org.arsen.forex.service.lookup.CustomerDetails;
import org.arsen.forex.service.lookup.RateDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public final class ForexServiceFacadeTestHelper {

    private ForexServiceFacadeTestHelper() {
        throw new UnsupportedOperationException();
    }

    public static CustomerCreationRequest customerCreationRequest() {
        var creationRequest = new CustomerCreationRequest();
        creationRequest.username(randomUUID());
        creationRequest.email(randomEmail());
        creationRequest.dateOfBirth(LocalDate.now().minusYears(20));
        return creationRequest;
    }

    public static AccountCreationRequest accountCreationRequest() {
        var request = new AccountCreationRequest();
        request.setBalance(BigDecimal.TEN);
        request.setCurrency(CurrencyType.RUB);
        return request;
    }

    public static CustomerDetails customerDetails() {
        return new DummyCustomerDetails();
    }

    public static CustomerDetailsDto customerDetailsDto() {
        final var detailsDto = new CustomerDetailsDto();

        detailsDto.setId(RandomUtils.nextLong());
        detailsDto.setUsername(randomUUID());
        detailsDto.setEmail(randomEmail());
        detailsDto.setDateOfBirth(LocalDate.now().minusYears(20));
        return detailsDto;
    }

    public static AccountDetails accountDetails() {
        return new DummyAccountDetails(CurrencyType.RUB);
    }

    public static RateDetails rateDetails() {
        return new DummyRateDetails(CurrencyType.RUB, CurrencyType.AMD);
    }

    private static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    // need migrate same util for generate random email
    private static String randomEmail() {
        return "testuser@gmail.com";
    }

    private static final class DummyCustomerDetails implements CustomerDetails {

        private final Long id = RandomUtils.nextLong();

        private final String username = randomUUID();
        private final String email = randomEmail();

        private final LocalDate dateOfBirth = LocalDate.now().minusYears(20);

        @Override
        public Long id() {
            return id;
        }

        @Override
        public String username() {
            return username;
        }

        @Override
        public String email() {
            return email;
        }

        @Override
        public LocalDate dateOfBirth() {
            return dateOfBirth;
        }
    }

    private static final class DummyAccountDetails implements AccountDetails {

        private String number;
        private final CurrencyType currency;
        private BigDecimal balance;
        private boolean isDisabled;

        private DummyAccountDetails(final CurrencyType currency) {
            this.currency = currency;
        }

        @Override
        public String number() {
            return number;
        }

        @Override
        public CurrencyType currency() {
            return currency;
        }

        @Override
        public BigDecimal balance() {
            return balance;
        }

        @Override
        public boolean isDisabled() {
            return isDisabled;
        }
    }

    private static final class DummyRateDetails implements RateDetails {

        private final CurrencyType from;
        private final CurrencyType to;
        private Long id;
        private BigDecimal rate;

        private DummyRateDetails(final CurrencyType from, CurrencyType to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long id() {
            return id;
        }

        @Override
        public CurrencyType from() {
            return from;
        }

        @Override
        public CurrencyType to() {
            return to;
        }

        @Override
        public BigDecimal rate() {
            return rate;
        }
    }
}