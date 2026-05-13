package xyzbank.interview.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyzbank.interview.assignment.entity.Customer;
import xyzbank.interview.assignment.entity.Document;

import java.util.Optional;

public interface DocumentRepository
        extends JpaRepository<Document, Long> {

    Optional<Document> findByCustomer(
            Customer customer
    );

    Optional<Document> findByCustomerId(
            Long customerId
    );
}