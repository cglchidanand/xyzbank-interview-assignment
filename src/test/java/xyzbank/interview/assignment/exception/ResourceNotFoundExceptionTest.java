package xyzbank.interview.assignment.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void shouldCreateResourceNotFoundException() {

        ResourceNotFoundException exception =
                new ResourceNotFoundException(
                        "Resource Not Found"
                );

        assertEquals(
                "Resource Not Found",
                exception.getMessage()
        );

        assertNotNull(exception);
    }
}

