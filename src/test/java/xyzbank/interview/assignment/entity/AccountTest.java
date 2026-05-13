 package xyzbank.interview.assignment.entity;

import org.junit.jupiter.api.Test;

import xyzbank.interview.assignment.enums.AccountType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldTestAccountBuilderAndGetters() {

        Account account =
                Account.builder()
                        .id(1L)
                        .accountNumber("ACC12345")
                        .accountType(AccountType.SAVINGS)
                        .balance(BigDecimal.valueOf(1000.0))
                        .build();

        assertEquals(1L, account.getId());
        assertEquals("ACC12345", account.getAccountNumber());
        assertEquals(AccountType.SAVINGS, account.getAccountType());
        assertEquals(
                BigDecimal.valueOf(1000.0),
                account.getBalance()
        );

        assertNotNull(account);
    }
}

