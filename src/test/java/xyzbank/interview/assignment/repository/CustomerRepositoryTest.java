package xyzbank.interview.assignment.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import xyzbank.interview.assignment.entity.Customer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveCustomerSuccessfully() {

        Customer customer =
                Customer.builder()
                        .fullName("John Doe")
                        .username("john123")
                        .password("password")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(LocalDate.of(1990,1,1))
                        .active(true)
                        .build();

        Customer saved =
                customerRepository.save(customer);

        assertNotNull(saved.getId());
    }
}