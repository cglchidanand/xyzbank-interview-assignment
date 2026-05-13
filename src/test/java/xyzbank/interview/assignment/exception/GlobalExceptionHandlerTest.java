package xyzbank.interview.assignment.exception;

import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import xyzbank.interview.assignment.dto.response.ApiResponse;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldHandleResourceNotFoundException() {

        ResponseEntity<ApiResponse<Object>> response =
                handler.handleNotFound(
                        new ResourceNotFoundException("Not Found")
                );

        assertEquals(404, response.getStatusCodeValue());

        assertEquals(
                "Not Found",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleBadRequestException() {

        ResponseEntity<ApiResponse<Object>> response =
                handler.handleBadRequest(
                        new BadRequestException("Bad Request")
                );

        assertEquals(400, response.getStatusCodeValue());

        assertEquals(
                "Bad Request",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleUnauthorizedException() {

        ResponseEntity<ApiResponse<Object>> response =
                handler.handleUnauthorized(
                        new UnauthorizedException("Unauthorized")
                );

        assertEquals(401, response.getStatusCodeValue());

        assertEquals(
                "Unauthorized",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleBadCredentialsException() {

        ResponseEntity<ApiResponse<Object>> response =
                handler.handleBadCredentials(
                        new BadCredentialsException("Invalid")
                );

        assertEquals(401, response.getStatusCodeValue());

        assertEquals(
                "Invalid username or password",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleIllegalArgumentException() {

        ResponseEntity<ApiResponse<Object>> response =
                handler.handleIllegalArgument(
                        new IllegalArgumentException("Illegal Argument")
                );

        assertEquals(400, response.getStatusCodeValue());

        assertEquals(
                "Illegal Argument",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleConstraintViolationException() {

        ResponseEntity<ApiResponse<Object>> response =
                handler.handleConstraint(
                        new ConstraintViolationException(
                                "Constraint Error",
                                null
                        )
                );

        assertEquals(400, response.getStatusCodeValue());

        assertEquals(
                "Constraint Error",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleGenericException() {

        ResponseEntity<ApiResponse<Object>> response =
                handler.handleGeneric(
                        new Exception("Error")
                );

        assertEquals(500, response.getStatusCodeValue());

        assertEquals(
                "Something went wrong",
                response.getBody().getMessage()
        );
    }
}

