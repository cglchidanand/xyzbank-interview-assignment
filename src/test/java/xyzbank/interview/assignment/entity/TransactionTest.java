 package xyzbank.interview.assignment.entity;

import org.junit.jupiter.api.Test;

import xyzbank.interview.assignment.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void shouldTestGetterSetter() {

        LocalDateTime now =
                LocalDateTime.now();

        Transaction transaction =
                new Transaction();

        transaction.setId(1L);

        transaction.setAmount(
                BigDecimal.valueOf(1000)
        );

        transaction.setTransactionType(
                TransactionType.DEPOSIT
        );

        transaction.setTransactionDate(now);

        assertEquals(
                1L,
                transaction.getId()
        );

        assertEquals(
                BigDecimal.valueOf(1000),
                transaction.getAmount()
        );

        assertEquals(
                TransactionType.DEPOSIT,
                transaction.getTransactionType()
        );

        assertEquals(
                now,
                transaction.getTransactionDate()
        );
    }

    @Test
    void shouldTestBuilder() {

        LocalDateTime now =
                LocalDateTime.now();

        Transaction transaction =
                Transaction.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(500))
                        .transactionType(TransactionType.WITHDRAW)
                        .transactionDate(now)
                        .build();

        assertNotNull(transaction);

        assertEquals(
                1L,
                transaction.getId()
        );

        assertEquals(
                BigDecimal.valueOf(500),
                transaction.getAmount()
        );

        assertEquals(
                TransactionType.WITHDRAW,
                transaction.getTransactionType()
        );

        assertEquals(
                now,
                transaction.getTransactionDate()
        );
    }

    @Test
    void shouldTestEqualsHashCodeAndToString() {

        LocalDateTime now =
                LocalDateTime.now();

        Transaction transaction1 =
                Transaction.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(500))
                        .transactionType(TransactionType.DEPOSIT)
                        .transactionDate(now)
                        .build();

        Transaction transaction2 =
                Transaction.builder()
                        .id(1L)
                        .amount(BigDecimal.valueOf(500))
                        .transactionType(TransactionType.DEPOSIT)
                        .transactionDate(now)
                        .build();

        assertEquals(transaction1, transaction2);

        assertEquals(
                transaction1.hashCode(),
                transaction2.hashCode()
        );

        assertNotNull(transaction1.toString());
    }

    @Test
    void shouldTestNoArgsConstructor() {

        Transaction transaction =
                new Transaction();

        assertNotNull(transaction);
    }

    @Test
    void shouldTestAllFieldsWithAccount() {

        Account account =
                Account.builder()
                        .id(1L)
                        .accountNumber("ACC123")
                        .build();

        LocalDateTime now =
                LocalDateTime.now();

        Transaction transaction =
                Transaction.builder()
                        .id(10L)
                        .amount(BigDecimal.valueOf(2500))
                        .transactionType(TransactionType.DEPOSIT)
                        .transactionDate(now)
                        .account(account)
                        .build();

        assertEquals(
                10L,
                transaction.getId()
        );

        assertEquals(
                BigDecimal.valueOf(2500),
                transaction.getAmount()
        );

        assertEquals(
                TransactionType.DEPOSIT,
                transaction.getTransactionType()
        );

        assertEquals(
                now,
                transaction.getTransactionDate()
        );

        assertNotNull(transaction.getAccount());

        assertEquals(
                "ACC123",
                transaction.getAccount().getAccountNumber()
        );
    }
}
 