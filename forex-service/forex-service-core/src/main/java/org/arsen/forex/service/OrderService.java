package org.arsen.forex.service;

import org.arsen.forex.service.lookup.LookupOrderDetailsResult;
import org.arsen.forex.service.order.OrderParameters;
import org.arsen.forex.service.order.OrderResult;

public interface OrderService {

    OrderResult order(OrderParameters parameters);

    LookupOrderDetailsResult lookupDetails(Long id);
}