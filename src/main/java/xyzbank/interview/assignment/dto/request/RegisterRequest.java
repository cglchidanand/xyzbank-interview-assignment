 package xyzbank.interview.assignment.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import xyzbank.interview.assignment.enums.AccountType;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(
        name = "Register Request",
        description =
                "Request object used for customer registration"
)

public class RegisterRequest {

    @Schema(
            description = "Customer full name",
            example = "Chidananda"
    )

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Schema(
            description = "Customer email address",
            example = "chidu@gmail.com"
    )

    @Email(message = "Invalid email")

    @NotBlank(message = "Email is required")
    private String email;

    @Schema(
            description = "Customer country",
            example = "Netherlands"
    )

    @NotBlank(message = "Country is required")
    private String country;

    @Schema(
            description =
                    "Customer date of birth in yyyy-MM-dd format",

            example = "1999-01-01"
    )

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @Schema(
            description =
                    """
                    Supported account types:
                    - SAVINGS
                    - SALARY
                    - CURRENT

                    Accepted inputs:
                    savings
                    saving
                    saving account
                    current
                    current account
                    salary
                    salary account
                    """,

            example = "SAVINGS"
    )

    @NotNull(message = "Account type is required")
    private AccountType accountType;
}
 