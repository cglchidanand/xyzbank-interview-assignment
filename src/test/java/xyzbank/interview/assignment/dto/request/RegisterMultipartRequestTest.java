package xyzbank.interview.assignment.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterMultipartRequestTest {

    @Test
    void shouldTestSettersAndGetters() {

        RegisterMultipartRequest request =
                new RegisterMultipartRequest();

        request.setFullName("Chidananda");
        request.setEmail("chidu@gmail.com");
        request.setCountry("Netherlands");
        request.setDateOfBirth("1992-01-01");
        request.setAccountType("savings");

        assertEquals(
                "Chidananda",
                request.getFullName()
        );

        assertEquals(
                "chidu@gmail.com",
                request.getEmail()
        );

        assertEquals(
                "Netherlands",
                request.getCountry()
        );

        assertEquals(
                "1992-01-01",
                request.getDateOfBirth()
        );

        assertEquals(
                "savings",
                request.getAccountType()
        );
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        RegisterMultipartRequest request1 =
                new RegisterMultipartRequest();

        request1.setFullName("Chidananda");
        request1.setEmail("chidu@gmail.com");

        RegisterMultipartRequest request2 =
                new RegisterMultipartRequest();

        request2.setFullName("Chidananda");
        request2.setEmail("chidu@gmail.com");

        assertEquals(request1, request2);

        assertEquals(
                request1.hashCode(),
                request2.hashCode()
        );
    }

    @Test
    void shouldTestToString() {

        RegisterMultipartRequest request =
                new RegisterMultipartRequest();

        request.setFullName("Chidananda");

        String result =
                request.toString();

        assertTrue(
                result.contains("Chidananda")
        );
    }
}

