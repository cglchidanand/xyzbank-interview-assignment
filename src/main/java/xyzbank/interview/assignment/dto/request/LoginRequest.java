 package xyzbank.interview.assignment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(
        name = "Login Request",
        description = "Request object for customer login"
)

public class LoginRequest {

    @Schema(
            description = "Customer username",
            example = "john123"
    )

    @NotBlank(message = "Username is required")
    private String username;

    @Schema(
            description = "Customer password",
            example = "Pass@123"
    )

    @NotBlank(message = "Password is required")
    private String password;
}
 