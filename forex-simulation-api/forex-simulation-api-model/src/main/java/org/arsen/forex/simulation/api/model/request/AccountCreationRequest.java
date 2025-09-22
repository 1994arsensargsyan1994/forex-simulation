package org.arsen.forex.simulation.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.arsen.forex.simulation.api.model.common.AbstractRequest;
import org.arsen.forex.simulation.common.CurrencyType;

public class AccountCreationRequest extends AbstractRequest {

    @NotNull
    @JsonProperty("currency")
    private CurrencyType currency;

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(@NotNull CurrencyType currency) {
        this.currency = currency;
    }
}
