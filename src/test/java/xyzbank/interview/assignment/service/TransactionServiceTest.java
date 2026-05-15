 package xyzbank.interview.assignment.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import xyzbank.interview.assignment.dto.request.DepositRequest;
import xyzbank.interview.assignment.dto.request.WithdrawRequest;

import xyzbank.interview.assignment.dto.response.TransactionResponse;

import xyzbank.interview.assignment.entity.Account;
import xyzbank.interview.assignment.entity.Customer;
import xyzbank.interview.assignment.entity.Transaction;

import xyzbank.interview.assignment.enums.AccountType;
import xyzbank.interview.assignment.enums.CurrencyType;
import xyzbank.interview.assignment.enums.TransactionType;

import xyzbank.interview.assignment.exception.BadRequestException;

import xyzbank.interview.assignment.repository.AccountRepository;
import xyzbank.interview.assignment.repository.CustomerRepository;
import xyzbank.interview.assignment.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class TransactionServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Customer customer;

    private Account account;

    @BeforeEach
    void setup() {

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "john123",
                        null
                )
        );

        customer =
                Customer.builder()
                        .id(1L)
                        .username("john123")
                        .build();

        account =
                Account.builder()
                        .id(1L)
                        .accountNumber("ABNA123456789")
                        .balance(BigDecimal.valueOf(5000))
                        .currencyType(CurrencyType.EUR)
                        .accountType(AccountType.SAVINGS)
                        .customer(customer)
                        .build();
    }

    @AfterEach
    void cleanup() {

        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldDepositSuccessfully() {

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(customer))
                .thenReturn(Optional.of(account));

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.valueOf(1000))
                        .build();

        TransactionResponse response =
                transactionService.deposit(request);

        assertNotNull(response);

        assertEquals(
                "DEPOSIT",
                response.getTransactionType()
        );

        assertEquals(
                BigDecimal.valueOf(6000),
                response.getAvailableBalance()
        );

        verify(accountRepository, times(1))
                .save(account);

        verify(transactionRepository, times(1))
                .save(any(Transaction.class));
    }

    @Test
    void shouldFailDepositForInvalidAmount() {

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.ZERO)
                        .build();

        assertThrows(
                BadRequestException.class,
                () -> transactionService.deposit(request)
        );
    }

    @Test
    void shouldWithdrawSuccessfully() {

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(customer))
                .thenReturn(Optional.of(account));

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(BigDecimal.valueOf(1000))
                        .build();

        TransactionResponse response =
                transactionService.withdraw(request);

        assertNotNull(response);

        assertEquals(
                "WITHDRAW",
                response.getTransactionType()
        );

        assertEquals(
                BigDecimal.valueOf(4000),
                response.getAvailableBalance()
        );

        verify(accountRepository, times(1))
                .save(account);

        verify(transactionRepository, times(1))
                .save(any(Transaction.class));
    }

    @Test
    void shouldRejectWithdrawWhenBalanceInsufficient() {

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(customer))
                .thenReturn(Optional.of(account));

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(BigDecimal.valueOf(10000))
                        .build();

        assertThrows(
                BadRequestException.class,
                () -> transactionService.withdraw(request)
        );

        verify(transactionRepository, never())
                .save(any(Transaction.class));
    }

    @Test
    void shouldFailWithdrawForInvalidAmount() {

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(BigDecimal.ZERO)
                        .build();

        assertThrows(
                BadRequestException.class,
                () -> transactionService.withdraw(request)
        );
    }

    @Test
    void shouldReturnTransactionHistory() {

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(customer))
                .thenReturn(Optional.of(account));

        Transaction transaction =
                Transaction.builder()
                        .transactionType(TransactionType.DEPOSIT)
                        .amount(BigDecimal.valueOf(500))
                        .account(account)
                        .build();

        when(transactionRepository.findByAccount(account))
                .thenReturn(List.of(transaction));

        List<Transaction> transactions =
                transactionService.getTransactions();

        assertNotNull(transactions);

        assertFalse(transactions.isEmpty());

        assertEquals(
                1,
                transactions.size()
        );

        verify(transactionRepository, times(1))
                .findByAccount(account);
    }

    @Test
    void shouldReturnEmptyTransactionHistory() {

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(customer))
                .thenReturn(Optional.of(account));

        when(transactionRepository.findByAccount(account))
                .thenReturn(List.of());

        List<Transaction> transactions =
                transactionService.getTransactions();

        assertNotNull(transactions);

        assertTrue(transactions.isEmpty());

        verify(transactionRepository, times(1))
                .findByAccount(account);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.empty());

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.valueOf(100))
                        .build();

        assertThrows(
                RuntimeException.class,
                () -> transactionService.deposit(request)
        );

        verify(accountRepository, never())
                .findByCustomer(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(customer))
                .thenReturn(Optional.empty());

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.valueOf(100))
                        .build();

        assertThrows(
                RuntimeException.class,
                () -> transactionService.deposit(request)
        );

        verify(transactionRepository, never())
                .save(any(Transaction.class));
    }
}