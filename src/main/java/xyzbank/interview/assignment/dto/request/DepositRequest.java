package xyzbank.interview.assignment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Schema(
        description = "Deposit request payload"
)

public class DepositRequest {

    @Schema(
            description = "Amount to deposit",
            example = "1000.00"
    )

    @NotNull(message = "Amount is required")

    @DecimalMin(
            value = "0.01",
            message = "Amount must be greater than 0"
    )

    private BigDecimal amount;
}
