package org.arsen.forex.api.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.arsen.forex.api.model.request.CustomerCreationRequest;

import java.time.LocalDate;

public final class ControllerIntegrationTestHelper {

    static final Long CUSTOMER_ID = 1L;

    public static final @NotBlank
    @Size(min = 5, max = 255) String CUSTOMER_USERNAME = "usertest2";

    public static final @NotBlank
    @Size(min = 5, max = 255) String CUSTOMER_EMAIL = "usertest2@example.com";

    public static CustomerCreationRequest customerCreationRequest() {
        final var request = new CustomerCreationRequest();
        request.username(CUSTOMER_USERNAME);
        request.email(CUSTOMER_EMAIL);
        request.dateOfBirth(LocalDate.now().minusYears(20));
        return request;
    }
}