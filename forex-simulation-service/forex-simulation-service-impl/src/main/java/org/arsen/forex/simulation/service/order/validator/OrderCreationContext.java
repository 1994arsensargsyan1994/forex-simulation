package org.arsen.forex.simulation.service.order.validator;

import org.arsen.forex.simulation.persistence.account.PersistentAccount;
import org.arsen.forex.simulation.persistence.customer.PersistentCustomer;
import org.arsen.forex.simulation.service.order.OrderParameters;

public record OrderCreationContext(OrderParameters parameters, PersistentCustomer customer,
                                   PersistentAccount from, PersistentAccount to) {
}
