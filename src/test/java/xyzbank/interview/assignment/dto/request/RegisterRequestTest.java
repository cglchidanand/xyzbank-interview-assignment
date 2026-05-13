
package xyzbank.interview.assignment.dto.request;

import org.junit.jupiter.api.Test;

import xyzbank.interview.assignment.enums.AccountType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    @Test
    void shouldTestNoArgsConstructorAndSetters() {

        RegisterRequest request =
                new RegisterRequest();

        request.setFullName("John Doe");
        request.setEmail("john@test.com");
        request.setCountry("Netherlands");
        request.setDateOfBirth(
                LocalDate.of(1990, 1, 1)
        );
        request.setAccountType(AccountType.SAVINGS);

        assertEquals(
                "John Doe",
                request.getFullName()
        );

        assertEquals(
                "john@test.com",
                request.getEmail()
        );

        assertEquals(
                "Netherlands",
                request.getCountry()
        );

        assertEquals(
                LocalDate.of(1990, 1, 1),
                request.getDateOfBirth()
        );

        assertEquals(
                AccountType.SAVINGS,
                request.getAccountType()
        );
    }

    @Test
    void shouldTestAllArgsConstructor() {

        RegisterRequest request =
                new RegisterRequest(
                        "John Doe",
                        "john@test.com",
                        "Netherlands",
                        LocalDate.of(1990, 1, 1),
                        AccountType.SAVINGS
                );

        assertNotNull(request);

        assertEquals(
                "John Doe",
                request.getFullName()
        );

        assertEquals(
                "john@test.com",
                request.getEmail()
        );
    }

    @Test
    void shouldTestRegisterRequestBuilderAndGetters() {

        RegisterRequest request =
                RegisterRequest.builder()
                        .fullName("John Doe")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(
                                LocalDate.of(1990, 1, 1)
                        )
                        .accountType(AccountType.SAVINGS)
                        .build();

        assertEquals(
                "John Doe",
                request.getFullName()
        );

        assertEquals(
                "john@test.com",
                request.getEmail()
        );

        assertEquals(
                "Netherlands",
                request.getCountry()
        );

        assertEquals(
                AccountType.SAVINGS,
                request.getAccountType()
        );
    }
}

