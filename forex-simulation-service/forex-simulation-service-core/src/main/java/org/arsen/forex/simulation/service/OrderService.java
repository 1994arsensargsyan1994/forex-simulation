package org.arsen.forex.simulation.service;

import org.arsen.forex.simulation.service.order.OrderParameters;
import org.arsen.forex.simulation.service.order.OrderResult;

public interface OrderService {

    OrderResult order(OrderParameters parameters);
}