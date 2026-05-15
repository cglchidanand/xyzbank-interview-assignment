 package xyzbank.interview.assignment.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void shouldTestCustomerBuilderAndGetters() {

        LocalDate dob =
                LocalDate.of(1990, 1, 1);

        LocalDateTime createdAt =
                LocalDateTime.now();

        Customer customer =
                Customer.builder()
                        .id(1L)
                        .fullName("John Doe")
                        .username("john123")
                        .password("password")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(dob)
                        .active(true)
                        .createdAt(createdAt)
                        .build();

        assertEquals(1L, customer.getId());

        assertEquals(
                "John Doe",
                customer.getFullName()
        );

        assertEquals(
                "john123",
                customer.getUsername()
        );

        assertEquals(
                "password",
                customer.getPassword()
        );

        assertEquals(
                "john@test.com",
                customer.getEmail()
        );

        assertEquals(
                "Netherlands",
                customer.getCountry()
        );

        assertEquals(
                dob,
                customer.getDateOfBirth()
        );

        assertEquals(
                createdAt,
                customer.getCreatedAt()
        );

        assertTrue(customer.isActive());

        assertNotNull(customer);
    }

    @Test
    void shouldTestSetterAndGetter() {

        Customer customer = new Customer();

        LocalDate dob =
                LocalDate.of(1995, 5, 10);

        LocalDateTime createdAt =
                LocalDateTime.now();

        customer.setId(2L);

        customer.setFullName("Alice");

        customer.setUsername("alice123");

        customer.setPassword("secret");

        customer.setEmail("alice@test.com");

        customer.setCountry("India");

        customer.setDateOfBirth(dob);

        customer.setActive(false);

        customer.setCreatedAt(createdAt);

        assertEquals(2L, customer.getId());

        assertEquals(
                "Alice",
                customer.getFullName()
        );

        assertEquals(
                "alice123",
                customer.getUsername()
        );

        assertEquals(
                "secret",
                customer.getPassword()
        );

        assertEquals(
                "alice@test.com",
                customer.getEmail()
        );

        assertEquals(
                "India",
                customer.getCountry()
        );

        assertEquals(
                dob,
                customer.getDateOfBirth()
        );

        assertEquals(
                createdAt,
                customer.getCreatedAt()
        );

        assertFalse(customer.isActive());
    }

    @Test
    void shouldTestEqualsHashCodeAndToString() {

        LocalDate dob =
                LocalDate.of(1990, 1, 1);

        LocalDateTime createdAt =
                LocalDateTime.now();

        Customer customer1 =
                Customer.builder()
                        .id(1L)
                        .fullName("John Doe")
                        .username("john123")
                        .password("password")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(dob)
                        .active(true)
                        .createdAt(createdAt)
                        .build();

        Customer customer2 =
                Customer.builder()
                        .id(1L)
                        .fullName("John Doe")
                        .username("john123")
                        .password("password")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(dob)
                        .active(true)
                        .createdAt(createdAt)
                        .build();

        assertEquals(customer1, customer2);

        assertEquals(
                customer1.hashCode(),
                customer2.hashCode()
        );

        assertNotNull(customer1.toString());
    }

    @Test
    void shouldTestNoArgsConstructor() {

        Customer customer = new Customer();

        assertNotNull(customer);
    }

    @Test
    void shouldSetCreatedAtDuringPrePersist() {

        Customer customer = new Customer();

        customer.prePersist();

        assertNotNull(customer.getCreatedAt());
    }
}
 