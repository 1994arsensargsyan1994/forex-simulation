package org.arsen.forex.simulation.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.arsen.forex.simulation.api.model.common.AbstractRequest;
import org.arsen.forex.simulation.common.AccountType;
import org.arsen.forex.simulation.common.CurrencyType;

public class AccountCreationRequest extends AbstractRequest {

    @NotNull
    @JsonProperty("type")
    private AccountType type;

    @NotNull
    @JsonProperty("currency")
    private CurrencyType currency;

    public AccountType getType() {
        return type;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setType(@NotNull AccountType type) {
        this.type = type;
    }

    public void setCurrency(@NotNull CurrencyType currency) {
        this.currency = currency;
    }
}
