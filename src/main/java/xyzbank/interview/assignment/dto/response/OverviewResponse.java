 package xyzbank.interview.assignment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(
        name = "Overview Response",
        description =
                "Customer account overview response"
)

public class OverviewResponse {

    @Schema(
            description = "Unique bank account number",
            example = "ABNA123456789012"
    )

    private String accountNumber;

    @Schema(
            description = "Type of bank account",
            example = "SAVINGS"
    )

    private String accountType;

    @Schema(
            description = "Current account balance",
            example = "1500.75"
    )

    private BigDecimal balance;

    @Schema(
            description = "Account currency",
            example = "EUR"
    )

    private String currency;
}
 