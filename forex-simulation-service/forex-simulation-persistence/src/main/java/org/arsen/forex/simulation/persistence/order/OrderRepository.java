package org.arsen.forex.simulation.persistence.order;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface OrderRepository extends JpaRepositoryImplementation<PersistentOrder, Long> {
    Optional<PersistentOrder> findByIdempotencyKey(String idempotencyKey);
}
