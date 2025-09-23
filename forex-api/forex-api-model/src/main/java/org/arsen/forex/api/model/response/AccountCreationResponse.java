package org.arsen.forex.api.model.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.arsen.forex.common.api.model.FailureAwareResponse;

public class AccountCreationResponse extends FailureAwareResponse {

    private String number;

    public AccountCreationResponse() {
        super();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", getNumber())
                .toString();
    }
}
