 package xyzbank.interview.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class TransactionResponse {

    private String transactionType;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private BigDecimal availableBalance;
}

