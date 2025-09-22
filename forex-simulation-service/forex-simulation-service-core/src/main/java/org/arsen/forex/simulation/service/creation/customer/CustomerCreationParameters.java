package org.arsen.forex.simulation.service.creation.customer;

import java.time.LocalDate;

public record CustomerCreationParameters(String username, String email, LocalDate birthday) {

}
