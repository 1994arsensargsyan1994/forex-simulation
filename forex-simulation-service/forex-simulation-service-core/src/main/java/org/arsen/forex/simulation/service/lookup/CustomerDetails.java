package org.arsen.forex.simulation.service.lookup;

import java.time.LocalDate;

public interface CustomerDetails {

    Long id();
    String username();
    String email();
    LocalDate dateOfBirth();
}