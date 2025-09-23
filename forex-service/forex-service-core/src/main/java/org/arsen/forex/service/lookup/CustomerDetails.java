package org.arsen.forex.service.lookup;

import java.time.LocalDate;

public interface CustomerDetails {

    Long id();
    String username();
    String email();
    LocalDate dateOfBirth();
}