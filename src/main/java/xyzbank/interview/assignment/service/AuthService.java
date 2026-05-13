package xyzbank.interview.assignment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyzbank.interview.assignment.dto.request.LoginRequest;
import xyzbank.interview.assignment.dto.request.RegisterRequest;
import xyzbank.interview.assignment.dto.response.LoginResponse;
import xyzbank.interview.assignment.dto.response.RegisterResponse;
import xyzbank.interview.assignment.entity.Account;
import xyzbank.interview.assignment.entity.Customer;
import xyzbank.interview.assignment.entity.Document;
import xyzbank.interview.assignment.enums.CurrencyType;
import xyzbank.interview.assignment.enums.DocumentStatus;
import xyzbank.interview.assignment.exception.BadRequestException;
import xyzbank.interview.assignment.repository.AccountRepository;
import xyzbank.interview.assignment.repository.CustomerRepository;
import xyzbank.interview.assignment.repository.DocumentRepository;
import xyzbank.interview.assignment.security.JwtUtil;
import xyzbank.interview.assignment.util.AccountNumberGenerator;
import xyzbank.interview.assignment.util.PasswordGenerator;
import xyzbank.interview.assignment.util.UsernameGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    private final DocumentRepository documentRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final DocumentService documentService;

    public RegisterResponse register(
            RegisterRequest request,
            MultipartFile file
    ) {

        validateCountry(request.getCountry());

        validateAge(request.getDateOfBirth());

        validateEmail(request.getEmail());

        String username =
                UsernameGenerator.generateUsername(
                        request.getFullName()
                );

        String rawPassword =
                PasswordGenerator.generatePassword();

        Customer customer =
                Customer.builder()
                        .fullName(request.getFullName())
                        .username(username)
                        .password(
                                passwordEncoder.encode(rawPassword)
                        )
                        .email(request.getEmail())
                        .country(request.getCountry())
                        .dateOfBirth(request.getDateOfBirth())
                        .active(true)
                        .build();

        customerRepository.save(customer);

        Account account =
                Account.builder()
                        .accountNumber(
                                AccountNumberGenerator
                                        .generateAccountNumber()
                        )
                        .accountType(request.getAccountType())
                        .currencyType(CurrencyType.EUR)
                        .balance(BigDecimal.ZERO)
                        .customer(customer)
                        .build();

        accountRepository.save(account);

        Document document =
                Document.builder()
                        .customer(customer)
                        .documentStatus(DocumentStatus.PENDING)
                        .build();

        documentRepository.save(document);

        String status = "NOT_UPLOADED";

        if (file != null && !file.isEmpty()) {

            try {

                byte[] fileBytes =
                        file.getBytes();

                documentService.uploadDocumentAsync(
                        customer.getId(),
                        fileBytes,
                        file.getOriginalFilename(),
                        file.getContentType()
                );

                status = "PENDING";

            } catch (Exception ex) {

                throw new RuntimeException(
                        "File processing failed"
                );
            }
        }

        log.info("Customer registered successfully");

        return RegisterResponse.builder()
                .username(username)
                .generatedPassword(rawPassword)
                .accountNumber(account.getAccountNumber())
                .documentStatus(status)
                .build();
    }

    public LoginResponse login(
            LoginRequest request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token =
                jwtUtil.generateToken(
                        request.getUsername()
                );

        return LoginResponse.builder()
                .token(token)
                .username(request.getUsername())
                .build();
    }

    private void validateCountry(String country) {

        if (!country.equalsIgnoreCase("Netherlands")
                && !country.equalsIgnoreCase("Belgium")) {

            throw new BadRequestException(
                    "Only Netherlands and Belgium customers are allowed"
            );
        }
    }

    private void validateAge(LocalDate dob) {

        int age =
                Period.between(
                        dob,
                        LocalDate.now()
                ).getYears();

        if (age < 18) {

            throw new BadRequestException(
                    "Customer must be above 18 years"
            );
        }
    }

    private void validateEmail(String email) {

        if (customerRepository.existsByEmail(email)) {

            throw new BadRequestException(
                    "Email already exists"
            );
        }
    }
}