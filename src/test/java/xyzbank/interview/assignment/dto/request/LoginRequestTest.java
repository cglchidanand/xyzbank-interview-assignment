
package xyzbank.interview.assignment.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void shouldTestNoArgsConstructorAndSetters() {

        LoginRequest request =
                new LoginRequest();

        request.setUsername("john123");
        request.setPassword("password");

        assertEquals(
                "john123",
                request.getUsername()
        );

        assertEquals(
                "password",
                request.getPassword()
        );
    }

    @Test
    void shouldTestAllArgsConstructor() {

        LoginRequest request =
                new LoginRequest(
                        "john123",
                        "password"
                );

        assertNotNull(request);

        assertEquals(
                "john123",
                request.getUsername()
        );

        assertEquals(
                "password",
                request.getPassword()
        );
    }

    @Test
    void shouldTestBuilderAndGetters() {

        LoginRequest request =
                LoginRequest.builder()
                        .username("john123")
                        .password("password")
                        .build();

        assertEquals(
                "john123",
                request.getUsername()
        );

        assertEquals(
                "password",
                request.getPassword()
        );
    }
}

