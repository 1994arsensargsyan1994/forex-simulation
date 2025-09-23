package org.arsen.forex.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.arsen.forex.api.model.common.AbstractRequest;
import org.arsen.forex.api.model.validation.ValidEmail;

import java.time.LocalDate;

public class CustomerCreationRequest extends AbstractRequest {

    @NotBlank
    @Size(min = 5, max = 255)
    @JsonProperty("name")
    private String username;

    @NotBlank
    @ValidEmail
    @Size(min = 5 , max = 255)
    @JsonProperty("email")
    private String email;

    @Past
    @NotNull
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;

    public CustomerCreationRequest() {
        super();
    }

    public  String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public void username(@NotBlank @Size(min = 5, max = 255) String username) {
        this.username = username;
    }

    public void email(@NotBlank @Size(min = 5, max = 255) String email) {
        this.email = email;
    }

    public void dateOfBirth(@Past @NotNull LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
