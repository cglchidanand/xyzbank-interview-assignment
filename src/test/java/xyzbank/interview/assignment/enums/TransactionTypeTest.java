package xyzbank.interview.assignment.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTypeTest {

    @Test
    void shouldContainDeposit() {

        assertEquals(
                "DEPOSIT",
                TransactionType.DEPOSIT.name()
        );
    }

    @Test
    void shouldContainWithdraw() {

        assertEquals(
                "WITHDRAW",
                TransactionType.WITHDRAW.name()
        );
    }

    @Test
    void shouldTestValues() {

        TransactionType[] values =
                TransactionType.values();

        assertEquals(2, values.length);
    }

    @Test
    void shouldTestValueOf() {

        TransactionType type =
                TransactionType.valueOf("DEPOSIT");

        assertEquals(
                TransactionType.DEPOSIT,
                type
        );
    }
}