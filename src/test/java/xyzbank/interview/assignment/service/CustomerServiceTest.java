
package xyzbank.interview.assignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import xyzbank.interview.assignment.dto.response.OverviewResponse;

import xyzbank.interview.assignment.entity.Account;
import xyzbank.interview.assignment.entity.Customer;

import xyzbank.interview.assignment.enums.AccountType;
import xyzbank.interview.assignment.enums.CurrencyType;

import xyzbank.interview.assignment.repository.AccountRepository;
import xyzbank.interview.assignment.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldReturnOverviewSuccessfully() {

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "john123",
                        null
                )
        );

        Customer customer =
                Customer.builder()
                        .id(1L)
                        .username("john123")
                        .build();

        Account account =
                Account.builder()
                        .accountNumber("ABNA123456789012")
                        .accountType(AccountType.SAVINGS)
                        .currencyType(CurrencyType.EUR)
                        .balance(BigDecimal.valueOf(5000))
                        .customer(customer)
                        .build();

        when(customerRepository.findByUsername(anyString()))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(any(Customer.class)))
                .thenReturn(Optional.of(account));

        OverviewResponse response =
                customerService.getOverview();

        assertEquals(
                "ABNA123456789012",
                response.getAccountNumber()
        );

        assertEquals(
                "SAVINGS",
                response.getAccountType()
        );

        assertEquals(
                "EUR",
                response.getCurrency()
        );
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "john123",
                        null
                )
        );

        when(customerRepository.findByUsername(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> customerService.getOverview()
        );
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "john123",
                        null
                )
        );

        Customer customer =
                Customer.builder()
                        .id(1L)
                        .username("john123")
                        .build();

        when(customerRepository.findByUsername(anyString()))
                .thenReturn(Optional.of(customer));

        when(accountRepository.findByCustomer(any(Customer.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> customerService.getOverview()
        );
    }
}

