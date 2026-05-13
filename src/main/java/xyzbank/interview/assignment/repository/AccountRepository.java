package xyzbank.interview.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyzbank.interview.assignment.entity.Account;
import xyzbank.interview.assignment.entity.Customer;

import java.util.Optional;

public interface AccountRepository
        extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByCustomer(Customer customer);

}