 package xyzbank.interview.assignment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(
        name = "Login Response",
        description =
                "Response returned after successful customer login"
)

public class LoginResponse {

    @Schema(
            description = "JWT authentication token",
            example =
                    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huMTIzIn0.signature"
    )

    private String token;

    @Schema(
            description = "Customer username",
            example = "john123"
    )

    private String username;
}
 