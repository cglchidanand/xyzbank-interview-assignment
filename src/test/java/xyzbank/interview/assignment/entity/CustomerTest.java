 
package xyzbank.interview.assignment.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void shouldTestCustomerBuilderAndGetters() {

        Customer customer =
                Customer.builder()
                        .id(1L)
                        .fullName("John Doe")
                        .username("john123")
                        .password("password")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(LocalDate.of(1990, 1, 1))
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .build();

        assertEquals(1L, customer.getId());
        assertEquals("John Doe", customer.getFullName());
        assertEquals("john123", customer.getUsername());
        assertEquals("password", customer.getPassword());
        assertEquals("john@test.com", customer.getEmail());
        assertEquals("Netherlands", customer.getCountry());

        assertTrue(customer.isActive());

        assertNotNull(customer.getDateOfBirth());
        assertNotNull(customer.getCreatedAt());
        assertNotNull(customer);
    }

    @Test
    void shouldSetCreatedAtDuringPrePersist() {

        Customer customer = new Customer();

        customer.prePersist();

        assertNotNull(customer.getCreatedAt());
    }
}

