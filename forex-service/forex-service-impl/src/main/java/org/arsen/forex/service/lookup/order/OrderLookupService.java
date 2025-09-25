package org.arsen.forex.service.lookup.order;

import org.arsen.forex.persistence.order.PersistentOrder;

import java.util.Optional;

public interface OrderLookupService {

    Optional<PersistentOrder> lookup(Long orderId);
}
