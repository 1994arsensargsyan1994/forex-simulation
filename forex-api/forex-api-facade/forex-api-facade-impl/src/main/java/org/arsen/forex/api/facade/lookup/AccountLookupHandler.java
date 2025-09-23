package org.arsen.forex.api.facade.lookup;

import org.arsen.forex.api.model.response.LookupAccountResponse;

public interface AccountLookupHandler {

    LookupAccountResponse lookup(Long customerId);
}