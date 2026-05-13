package xyzbank.interview.assignment.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsernameGeneratorTest {

    @Test
    void shouldGenerateUsernameSuccessfully() {

        String username =
                UsernameGenerator.generateUsername(
                        "John Doe"
                );

        assertNotNull(username);

        assertTrue(
                username.startsWith("johndoe")
        );

        assertTrue(
                username.length() > 8
        );
    }

    @Test
    void shouldGenerateDifferentUsernames() {

        String username1 =
                UsernameGenerator.generateUsername(
                        "John Doe"
                );

        String username2 =
                UsernameGenerator.generateUsername(
                        "John Doe"
                );

        assertNotEquals(
                username1,
                username2
        );
    }
}
