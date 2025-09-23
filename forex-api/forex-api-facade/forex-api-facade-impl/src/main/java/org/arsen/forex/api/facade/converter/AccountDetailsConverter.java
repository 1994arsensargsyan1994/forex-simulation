package org.arsen.forex.api.facade.converter;

import org.arsen.forex.api.model.response.details.AccountDetailsDto;
import org.arsen.forex.service.lookup.AccountDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public interface AccountDetailsConverter extends Converter<AccountDetails, AccountDetailsDto> {

    default Collection<AccountDetailsDto> convertAll(final Collection<AccountDetails> accounts) {
        Assert.notNull(accounts, "Null was passed as an argument for parameter 'accounts'.");
        return accounts.stream().map(this::convert).filter(Objects::nonNull).collect(Collectors.toUnmodifiableList());
    }
}