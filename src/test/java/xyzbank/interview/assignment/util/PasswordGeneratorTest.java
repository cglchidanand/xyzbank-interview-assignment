package xyzbank.interview.assignment.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void shouldGeneratePasswordSuccessfully() {

        String password =
                PasswordGenerator.generatePassword();

        assertNotNull(password);

        assertEquals(
                10,
                password.length()
        );
    }

    @Test
    void shouldGenerateDifferentPasswords() {

        String password1 =
                PasswordGenerator.generatePassword();

        String password2 =
                PasswordGenerator.generatePassword();

        assertNotEquals(
                password1,
                password2
        );
    }
}

