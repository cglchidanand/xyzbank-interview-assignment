 package xyzbank.interview.assignment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(
        name = "Register Response",
        description =
                "Response returned after successful customer registration"
)

public class RegisterResponse {

    @Schema(
            description = "Generated username for customer login",
            example = "john123"
    )

    private String username;

    @Schema(
            description = "Auto-generated temporary password",
            example = "Temp@1234"
    )

    private String generatedPassword;

    @Schema(
            description = "Generated 16-character bank account number",
            example = "ABNA123456789012"
    )

    private String accountNumber;

    @Schema(
            description = "Document upload processing status",
            example = "PENDING"
    )

    private String documentStatus;
}
 