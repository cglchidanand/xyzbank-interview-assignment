 package xyzbank.interview.assignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mock.web.MockMultipartFile;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import xyzbank.interview.assignment.dto.request.LoginRequest;
import xyzbank.interview.assignment.dto.request.RegisterRequest;

import xyzbank.interview.assignment.enums.AccountType;

import xyzbank.interview.assignment.exception.BadRequestException;
import xyzbank.interview.assignment.exception.UnauthorizedException;

import xyzbank.interview.assignment.repository.AccountRepository;
import xyzbank.interview.assignment.repository.CustomerRepository;
import xyzbank.interview.assignment.repository.DocumentRepository;

import xyzbank.interview.assignment.security.JwtUtil;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private DocumentService documentService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterCustomerSuccessfully() {

        RegisterRequest request =
                RegisterRequest.builder()
                        .fullName("John Doe")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(LocalDate.of(1990, 1, 1))
                        .accountType(AccountType.SAVINGS)
                        .build();

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        "sample".getBytes()
                );

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        assertDoesNotThrow(() ->
                authService.register(request, file)
        );
    }

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest request =
                LoginRequest.builder()
                        .username("john123")
                        .password("password")
                        .build();

        when(authenticationManager.authenticate(any()))
                .thenReturn(null);

        when(jwtUtil.generateToken(anyString()))
                .thenReturn("jwt-token");

        assertDoesNotThrow(() ->
                authService.login(request)
        );
    }

    @Test
    void shouldRejectUnsupportedCountry() {

        RegisterRequest request =
                RegisterRequest.builder()
                        .fullName("John Doe")
                        .email("john@test.com")
                        .country("India")
                        .dateOfBirth(LocalDate.of(1990, 1, 1))
                        .accountType(AccountType.SAVINGS)
                        .build();

        BadRequestException exception =
                assertThrows(
                        BadRequestException.class,
                        () -> authService.register(request, null)
                );

        assertEquals(
                "Only Netherlands and Belgium customers are allowed",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectUnderageCustomer() {

        RegisterRequest request =
                RegisterRequest.builder()
                        .fullName("John Doe")
                        .email("john@test.com")
                        .country("Netherlands")
                        .dateOfBirth(LocalDate.now().minusYears(15))
                        .accountType(AccountType.SAVINGS)
                        .build();

        BadRequestException exception =
                assertThrows(
                        BadRequestException.class,
                        () -> authService.register(request, null)
                );

        assertEquals(
                "Customer must be above 18 years",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectInvalidLogin() {

        LoginRequest request =
                LoginRequest.builder()
                        .username("wronguser")
                        .password("wrongpass")
                        .build();

        when(
                authenticationManager.authenticate(any())
        ).thenThrow(
                new UnauthorizedException(
                        "Invalid username or password"
                )
        );

        assertThrows(
                UnauthorizedException.class,
                () -> authService.login(request)
        );
    }
}

