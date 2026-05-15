
package xyzbank.interview.assignment.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import xyzbank.interview.assignment.dto.request.DepositRequest;
import xyzbank.interview.assignment.dto.request.WithdrawRequest;

import xyzbank.interview.assignment.dto.response.TransactionResponse;

import xyzbank.interview.assignment.entity.Account;
import xyzbank.interview.assignment.entity.Customer;
import xyzbank.interview.assignment.entity.Transaction;

import xyzbank.interview.assignment.enums.TransactionType;

import xyzbank.interview.assignment.exception.BadRequestException;
import xyzbank.interview.assignment.exception.ResourceNotFoundException;

import xyzbank.interview.assignment.repository.AccountRepository;
import xyzbank.interview.assignment.repository.CustomerRepository;
import xyzbank.interview.assignment.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TransactionService {

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    public TransactionResponse deposit(
            DepositRequest request
    ) {

        validateAmount(request.getAmount());

        Account account = getLoggedInAccount();

        account.setBalance(
                account.getBalance().add(request.getAmount())
        );

        accountRepository.save(account);

        Transaction transaction =
                Transaction.builder()
                        .amount(request.getAmount())
                        .transactionType(TransactionType.DEPOSIT)
                        .transactionDate(LocalDateTime.now())
                        .account(account)
                        .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .transactionType("DEPOSIT")
                .amount(request.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .availableBalance(account.getBalance())
                .build();
    }

    public TransactionResponse withdraw(
            WithdrawRequest request
    ) {

        validateAmount(request.getAmount());

        Account account = getLoggedInAccount();

        if (
                account.getBalance().compareTo(request.getAmount()) < 0
        ) {

            throw new BadRequestException(
                    "Insufficient balance"
            );
        }

        account.setBalance(
                account.getBalance().subtract(request.getAmount())
        );

        accountRepository.save(account);

        Transaction transaction =
                Transaction.builder()
                        .amount(request.getAmount())
                        .transactionType(TransactionType.WITHDRAW)
                        .transactionDate(LocalDateTime.now())
                        .account(account)
                        .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .transactionType("WITHDRAW")
                .amount(request.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .availableBalance(account.getBalance())
                .build();
    }

    public List<Transaction> getTransactions() {

        Account account = getLoggedInAccount();

        return transactionRepository.findByAccount(account);
    }

    private Account getLoggedInAccount() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String username = authentication.getName();

        Customer customer =
                customerRepository
                        .findByUsername(username)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found"
                                )
                        );

        return accountRepository
                .findByCustomer(customer)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found"
                        )
                );
    }

    private void validateAmount(
            BigDecimal amount
    ) {

        if (
                amount == null
                        || amount.compareTo(BigDecimal.ZERO) <= 0
        ) {

            throw new BadRequestException(
                    "Amount must be greater than zero"
            );
        }
    }
}

