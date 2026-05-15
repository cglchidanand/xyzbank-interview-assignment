package xyzbank.interview.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyzbank.interview.assignment.entity.Account;
import xyzbank.interview.assignment.entity.Transaction;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);
}