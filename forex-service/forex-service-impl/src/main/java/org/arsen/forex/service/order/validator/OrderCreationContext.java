package org.arsen.forex.service.order.validator;

import org.arsen.forex.persistence.account.PersistentAccount;
import org.arsen.forex.persistence.customer.PersistentCustomer;
import org.arsen.forex.service.order.OrderParameters;

public record OrderCreationContext(OrderParameters parameters, PersistentCustomer customer,
                                   PersistentAccount from, PersistentAccount to) {
}
