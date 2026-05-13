package xyzbank.interview.assignment.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {

    @Test
    void shouldCreateBadRequestException() {

        BadRequestException exception =
                new BadRequestException(
                        "Invalid Request"
                );

        assertEquals(
                "Invalid Request",
                exception.getMessage()
        );

        assertNotNull(exception);
    }
}

