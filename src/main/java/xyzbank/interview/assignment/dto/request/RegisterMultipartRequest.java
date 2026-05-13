package xyzbank.interview.assignment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data

@Schema(
        name = "Register Multipart Request",
        description =
                "Request object used for customer registration "
                        + "with personal details and account type"
)

public class RegisterMultipartRequest {

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
            description = "Customer date of birth in yyyy-MM-dd format",
            example = "1992-01-01"
    )

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @Schema(
            description =
                    """
                    Supported account types:
                    - savings
                    - saving
                    - saving account
                    - salary
                    - salary account
                    - current
                    - current account
                    """,

            example = "savings"
    )

    @NotBlank(message = "Account type is required")
    private String accountType;
}

