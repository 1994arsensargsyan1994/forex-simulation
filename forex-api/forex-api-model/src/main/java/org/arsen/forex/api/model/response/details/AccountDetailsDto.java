package org.arsen.forex.api.model.response.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.arsen.forex.common.CurrencyType;

import java.math.BigDecimal;

public class AccountDetailsDto {

    @JsonProperty("number")
    private String number;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("isDisabled")
    private boolean isDisabled;

    @JsonProperty("currency")
    private CurrencyType currency;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }
}