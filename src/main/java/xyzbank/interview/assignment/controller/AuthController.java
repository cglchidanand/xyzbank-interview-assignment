
package xyzbank.interview.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import xyzbank.interview.assignment.dto.request.LoginRequest;
import xyzbank.interview.assignment.dto.request.RegisterMultipartRequest;
import xyzbank.interview.assignment.dto.request.RegisterRequest;

import xyzbank.interview.assignment.dto.response.ApiResponse;
import xyzbank.interview.assignment.dto.response.LoginResponse;
import xyzbank.interview.assignment.dto.response.RegisterResponse;

import xyzbank.interview.assignment.enums.AccountType;

import xyzbank.interview.assignment.service.AuthService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor

@Tag(
        name = "Authentication APIs",
        description = "APIs for customer registration and login"
)

public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Register New Customer",
            description =
                    "Registers a new customer, creates bank account, "
                            + "generates username/password and uploads "
                            + "ID document asynchronously."
    )

    @ApiResponses(value = {

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Customer registered successfully"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation failed or invalid input",
                    content = @Content
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })

    @PostMapping(
            value = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )

    public ResponseEntity<ApiResponse<RegisterResponse>> register(

            @ModelAttribute
            @Valid
            RegisterMultipartRequest request,

            @RequestPart(value = "file", required = false)
            MultipartFile file
    ) {

        String input =
                request.getAccountType()
                        .trim()
                        .toUpperCase();

        AccountType accountType;

        switch (input) {

            case "SAVINGS":
            case "SAVING":
            case "SAVING ACCOUNT":
            case "SAVINGS ACCOUNT":

                accountType = AccountType.SAVINGS;
                break;

            case "SALARY":
            case "SALARY ACCOUNT":

                accountType = AccountType.SALARY;
                break;

            case "CURRENT":
            case "CURRENT ACCOUNT":

                accountType = AccountType.CURRENT;
                break;

            default:

                throw new IllegalArgumentException(
                        "Invalid account type. Allowed values: SAVINGS, SALARY or CURRENT"
                );
        }

        RegisterRequest registerRequest =
                RegisterRequest.builder()
                        .fullName(request.getFullName())
                        .email(request.getEmail())
                        .country(request.getCountry())
                        .dateOfBirth(
                                LocalDate.parse(
                                        request.getDateOfBirth()
                                )
                        )
                        .accountType(accountType)
                        .build();

        RegisterResponse response =
                authService.register(
                        registerRequest,
                        file
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Customer registered successfully",
                        response
                )
        );
    }

    @Operation(
            summary = "Customer Login",
            description =
                    "Authenticates customer using username and password "
                            + "and returns JWT token."
    )

    @ApiResponses(value = {

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Login successful"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Invalid username or password",
                    content = @Content
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation failed",
                    content = @Content
            )
    })

    @PostMapping("/logon")

    public ResponseEntity<ApiResponse<LoginResponse>> login(

            @Valid
            @RequestBody
            LoginRequest request
    ) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Login successful",
                        response
                )
        );
    }
}

