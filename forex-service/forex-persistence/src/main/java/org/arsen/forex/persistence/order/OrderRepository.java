package org.arsen.forex.persistence.order;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface OrderRepository extends JpaRepositoryImplementation<PersistentOrder, Long> {
    Optional<PersistentOrder> findByIdempotencyKey(String idempotencyKey);

    @EntityGraph(value = "order.withAccounts", type = EntityGraph.EntityGraphType.LOAD)
    Optional<PersistentOrder> findById(@NonNull Long id);
}
