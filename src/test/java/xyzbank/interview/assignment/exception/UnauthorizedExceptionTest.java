package xyzbank.interview.assignment.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnauthorizedExceptionTest {

    @Test
    void shouldCreateUnauthorizedException() {

        UnauthorizedException exception =
                new UnauthorizedException(
                        "Unauthorized Access"
                );

        assertEquals(
                "Unauthorized Access",
                exception.getMessage()
        );

        assertNotNull(exception);
    }
}

