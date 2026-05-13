package xyzbank.interview.assignment.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountNumberGeneratorTest {

    @Test
    void shouldGenerateValidAccountNumber() {

        String accountNumber =
                AccountNumberGenerator.generateAccountNumber();

        assertNotNull(accountNumber);

        assertTrue(
                accountNumber.startsWith("ABNA")
        );

        assertEquals(
                16,
                accountNumber.length()
        );
    }

    @Test
    void shouldGenerateDifferentAccountNumbers() {

        String accountNumber1 =
                AccountNumberGenerator.generateAccountNumber();

        String accountNumber2 =
                AccountNumberGenerator.generateAccountNumber();

        assertNotEquals(
                accountNumber1,
                accountNumber2
        );
    }
}

