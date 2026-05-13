package xyzbank.interview.assignment.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterResponseTest {

    @Test
    void shouldTestRegisterResponseBuilderAndGetters() {

        RegisterResponse response =
                RegisterResponse.builder()
                        .username("john123")
                        .generatedPassword("Temp@1234")
                        .accountNumber("ABNA123456789012")
                        .documentStatus("PENDING")
                        .build();

        assertEquals("john123", response.getUsername());

        assertEquals(
                "Temp@1234",
                response.getGeneratedPassword()
        );

        assertEquals(
                "ABNA123456789012",
                response.getAccountNumber()
        );

        assertEquals(
                "PENDING",
                response.getDocumentStatus()
        );

        assertNotNull(response);
    }

    @Test
    void shouldTestNoArgsConstructorAndSetters() {

        RegisterResponse response = new RegisterResponse();

        response.setUsername("sampleuser");
        response.setGeneratedPassword("Password123");
        response.setAccountNumber("ACC123456");
        response.setDocumentStatus("SUCCESS");

        assertEquals(
                "sampleuser",
                response.getUsername()
        );

        assertEquals(
                "Password123",
                response.getGeneratedPassword()
        );

        assertEquals(
                "ACC123456",
                response.getAccountNumber()
        );

        assertEquals(
                "SUCCESS",
                response.getDocumentStatus()
        );
    }

    @Test
    void shouldTestAllArgsConstructor() {

        RegisterResponse response =
                new RegisterResponse(
                        "john123",
                        "Temp@1234",
                        "ABNA123456789012",
                        "PENDING"
                );

        assertEquals("john123", response.getUsername());

        assertEquals(
                "Temp@1234",
                response.getGeneratedPassword()
        );

        assertEquals(
                "ABNA123456789012",
                response.getAccountNumber()
        );

        assertEquals(
                "PENDING",
                response.getDocumentStatus()
        );
    }
}

