package org.arsen.forex.service.lookup.order;

import org.arsen.forex.persistence.order.OrderRepository;
import org.arsen.forex.persistence.order.PersistentOrder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
class DefaultOrderLookupService implements OrderLookupService {

    private final OrderRepository orderRepository;

    public DefaultOrderLookupService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersistentOrder> lookup(Long customerId) {
        return orderRepository.findById(customerId);
    }
}