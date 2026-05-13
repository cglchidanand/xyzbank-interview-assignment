package xyzbank.interview.assignment.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void shouldTestApiResponseBuilderAndGetters() {

        ApiResponse<String> response =
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Success")
                        .data("Sample Data")
                        .build();

        assertTrue(response.isSuccess());
        assertEquals("Success", response.getMessage());
        assertEquals("Sample Data", response.getData());

        assertNotNull(response);
    }

    @Test
    void shouldTestSuccessStaticMethod() {

        ApiResponse<String> response =
                ApiResponse.success(
                        "Operation Successful",
                        "Data"
                );

        assertTrue(response.isSuccess());
        assertEquals(
                "Operation Successful",
                response.getMessage()
        );
        assertEquals("Data", response.getData());
    }

    @Test
    void shouldTestFailureStaticMethod() {

        ApiResponse<String> response =
                ApiResponse.failure(
                        "Operation Failed"
                );

        assertFalse(response.isSuccess());
        assertEquals(
                "Operation Failed",
                response.getMessage()
        );
        assertNull(response.getData());
    }
}

