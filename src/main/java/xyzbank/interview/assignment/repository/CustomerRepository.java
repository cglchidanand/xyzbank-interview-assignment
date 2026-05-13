package xyzbank.interview.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyzbank.interview.assignment.entity.Customer;

import java.util.Optional;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}