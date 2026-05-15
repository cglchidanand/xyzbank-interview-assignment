 
package xyzbank.interview.assignment.entity;

import org.junit.jupiter.api.Test;

import xyzbank.interview.assignment.enums.AccountType;
import xyzbank.interview.assignment.enums.CurrencyType;

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
                        .currencyType(CurrencyType.EUR)
                        .balance(BigDecimal.valueOf(1000.0))
                        .build();

        assertEquals(1L, account.getId());

        assertEquals(
                "ACC12345",
                account.getAccountNumber()
        );

        assertEquals(
                AccountType.SAVINGS,
                account.getAccountType()
        );

        assertEquals(
                CurrencyType.EUR,
                account.getCurrencyType()
        );

        assertEquals(
                BigDecimal.valueOf(1000.0),
                account.getBalance()
        );

        assertNotNull(account);
    }

    @Test
    void shouldTestSetterAndGetter() {

        Account account = new Account();

        account.setId(1L);

        account.setAccountNumber("ACC999");

        account.setAccountType(AccountType.CURRENT);

        account.setCurrencyType(CurrencyType.EUR);

        account.setBalance(BigDecimal.valueOf(5000));

        assertEquals(1L, account.getId());

        assertEquals(
                "ACC999",
                account.getAccountNumber()
        );

        assertEquals(
                AccountType.CURRENT,
                account.getAccountType()
        );

        assertEquals(
                CurrencyType.EUR,
                account.getCurrencyType()
        );

        assertEquals(
                BigDecimal.valueOf(5000),
                account.getBalance()
        );
    }

    @Test
    void shouldTestEqualsHashCodeAndToString() {

        Account account1 =
                Account.builder()
                        .id(1L)
                        .accountNumber("ACC123")
                        .accountType(AccountType.SAVINGS)
                        .currencyType(CurrencyType.EUR)
                        .balance(BigDecimal.valueOf(1000))
                        .build();

        Account account2 =
                Account.builder()
                        .id(1L)
                        .accountNumber("ACC123")
                        .accountType(AccountType.SAVINGS)
                        .currencyType(CurrencyType.EUR)
                        .balance(BigDecimal.valueOf(1000))
                        .build();

        assertEquals(account1, account2);

        assertEquals(
                account1.hashCode(),
                account2.hashCode()
        );

        assertNotNull(account1.toString());
    }

    @Test
    void shouldTestNoArgsConstructor() {

        Account account = new Account();

        assertNotNull(account);
    }

    @Test
    void shouldTestBuilderWithAllFields() {

        Account account =
                Account.builder()
                        .id(1L)
                        .accountNumber("ACC111")
                        .accountType(AccountType.SAVINGS)
                        .currencyType(CurrencyType.EUR)
                        .balance(BigDecimal.valueOf(3000))
                        .customer(null)
                        .build();

        assertEquals(1L, account.getId());

        assertEquals(
                "ACC111",
                account.getAccountNumber()
        );

        assertEquals(
                AccountType.SAVINGS,
                account.getAccountType()
        );

        assertEquals(
                CurrencyType.EUR,
                account.getCurrencyType()
        );

        assertEquals(
                BigDecimal.valueOf(3000),
                account.getBalance()
        );
    }
}
 