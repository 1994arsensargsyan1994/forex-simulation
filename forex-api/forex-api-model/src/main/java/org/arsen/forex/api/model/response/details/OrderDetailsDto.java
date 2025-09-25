package org.arsen.forex.api.model.response.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.arsen.forex.common.CurrencyType;
import org.arsen.forex.common.OrderStatus;

import java.math.BigDecimal;

public class OrderDetailsDto {

    @JsonProperty("currencyFrom")
    private CurrencyType currencyFrom;

    @JsonProperty("currencyTo")
    private CurrencyType currencyTo;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("rate")
    private BigDecimal rate;

    @JsonProperty("status")
    private OrderStatus status;

    @JsonProperty("failedReason")
    private String failedReason;

    public CurrencyType getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(CurrencyType currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(CurrencyType currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }
}
