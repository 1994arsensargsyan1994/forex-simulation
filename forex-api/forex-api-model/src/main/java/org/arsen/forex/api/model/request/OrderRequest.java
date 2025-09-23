package org.arsen.forex.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.arsen.forex.api.model.common.AbstractRequest;

import java.math.BigDecimal;

public class OrderRequest extends AbstractRequest {

    @NotNull
    @JsonProperty("fromAccountId")
    private String fromAccountId;

    @NotNull
    @JsonProperty("toAccountId")
    private String toAccountId;

    @JsonProperty("amount")
    @Positive
    @NotNull
    private BigDecimal amount;

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}