package xyzbank.interview.assignment.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import xyzbank.interview.assignment.entity.Customer;
import xyzbank.interview.assignment.repository.CustomerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void shouldLoadUserByUsernameSuccessfully() {

        Customer customer =
                Customer.builder()
                        .username("john123")
                        .password("password")
                        .build();

        when(customerRepository.findByUsername("john123"))
                .thenReturn(Optional.of(customer));

        UserDetails userDetails =
                customUserDetailsService
                        .loadUserByUsername("john123");

        assertNotNull(userDetails);

        assertEquals(
                "john123",
                userDetails.getUsername()
        );

        assertEquals(
                "password",
                userDetails.getPassword()
        );
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(customerRepository.findByUsername("wronguser"))
                .thenReturn(Optional.empty());

        assertThrows(
                UsernameNotFoundException.class,
                () -> customUserDetailsService
                        .loadUserByUsername("wronguser")
        );
    }
}
