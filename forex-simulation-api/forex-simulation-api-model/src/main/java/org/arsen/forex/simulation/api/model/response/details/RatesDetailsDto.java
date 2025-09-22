package org.arsen.forex.simulation.api.model.response.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.arsen.forex.simulation.common.CurrencyType;

import java.math.BigDecimal;

public class RatesDetailsDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("from")
    private CurrencyType from;

    @JsonProperty("to")
    private CurrencyType to;

    @JsonProperty("rate")
    private BigDecimal rate;

    public CurrencyType getFrom() {
        return from;
    }

    public void setFrom(CurrencyType from) {
        this.from = from;
    }

    public CurrencyType getTo() {
        return to;
    }

    public void setTo(CurrencyType to) {
        this.to = to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}