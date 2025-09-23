package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.model.response.details.AccountDetailsDto;
import org.arsen.forex.service.lookup.AccountDetails;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultAccountDetailsConverter implements AccountDetailsConverter {

    @Override
    public AccountDetailsDto convert(@NonNull final AccountDetails source) {
        Assert.notNull(source, "Null was passed as an argument for parameter 'source'.");
        final AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
        accountDetailsDto.setNumber(source.number());
        accountDetailsDto.setCurrency(source.currency());
        accountDetailsDto.setBalance(source.balance());
        accountDetailsDto.setDisabled(source.isDisabled());
        return accountDetailsDto;
    }
}