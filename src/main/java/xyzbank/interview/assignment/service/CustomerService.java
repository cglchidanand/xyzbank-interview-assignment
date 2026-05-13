package xyzbank.interview.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyzbank.interview.assignment.dto.response.OverviewResponse;
import xyzbank.interview.assignment.entity.Account;
import xyzbank.interview.assignment.entity.Customer;
import xyzbank.interview.assignment.exception.ResourceNotFoundException;
import xyzbank.interview.assignment.repository.AccountRepository;
import xyzbank.interview.assignment.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    public OverviewResponse getOverview() {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Customer customer =
                customerRepository
                        .findByUsername(username)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found"
                                )
                        );

        Account account =
                accountRepository
                        .findByCustomer(customer)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Account not found"
                                )
                        );

        return OverviewResponse.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(
                        account.getAccountType().name()
                )
                .balance(account.getBalance())
                .currency(
                        account.getCurrencyType().name()
                )
                .build();
    }
}