package org.arsen.forex.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.arsen.forex.api.model.common.AbstractRequest;
import org.arsen.forex.common.CurrencyType;

import java.math.BigDecimal;

public class AccountCreationRequest extends AbstractRequest {

    @NotNull
    @JsonProperty("currency")
    private CurrencyType currency;

    @PositiveOrZero
    @JsonProperty("balance")
    private BigDecimal balance = BigDecimal.ZERO;

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(@NotNull CurrencyType currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
