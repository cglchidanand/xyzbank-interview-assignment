package xyzbank.interview.assignment.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void shouldTestLoginResponseBuilderAndGetters() {

        LoginResponse response =
                LoginResponse.builder()
                        .token("jwt-token")
                        .username("john123")
                        .build();

        assertEquals("jwt-token", response.getToken());
        assertEquals("john123", response.getUsername());

        assertNotNull(response);
    }

    @Test
    void shouldTestNoArgsConstructorAndSetters() {

        LoginResponse response = new LoginResponse();

        response.setToken("sample-token");
        response.setUsername("sample-user");

        assertEquals("sample-token", response.getToken());
        assertEquals("sample-user", response.getUsername());
    }

    @Test
    void shouldTestAllArgsConstructor() {

        LoginResponse response =
                new LoginResponse(
                        "jwt-token",
                        "john123"
                );

        assertEquals("jwt-token", response.getToken());
        assertEquals("john123", response.getUsername());
    }
}

