package org.arsen.forex.simulation.persistence.customer;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepositoryImplementation<PersistentCustomer, Long> {

    Optional<PersistentCustomer> findCustomerByEmailOrUsername(String email , String username);
}
