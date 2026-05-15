package xyzbank.interview.assignment.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionResponseTest {

    @Test
    void shouldTestGetterSetter() {

        TransactionResponse response =
                new TransactionResponse();

        response.setTransactionType("DEPOSIT");

        response.setAmount(
                BigDecimal.valueOf(1000)
        );

        response.setAvailableBalance(
                BigDecimal.valueOf(5000)
        );

        response.setTransactionDate(
                LocalDateTime.now()
        );

        assertEquals(
                "DEPOSIT",
                response.getTransactionType()
        );
    }

    @Test
    void shouldTestBuilder() {

        TransactionResponse response =
                TransactionResponse.builder()
                        .transactionType("WITHDRAW")
                        .amount(BigDecimal.valueOf(500))
                        .availableBalance(BigDecimal.valueOf(4500))
                        .transactionDate(LocalDateTime.now())
                        .build();

        assertNotNull(response);
    }

    @Test
    void shouldTestEqualsHashCodeAndToString() {

        LocalDateTime now =
                LocalDateTime.now();

        TransactionResponse response1 =
                TransactionResponse.builder()
                        .transactionType("DEPOSIT")
                        .amount(BigDecimal.valueOf(500))
                        .availableBalance(BigDecimal.valueOf(1000))
                        .transactionDate(now)
                        .build();

        TransactionResponse response2 =
                TransactionResponse.builder()
                        .transactionType("DEPOSIT")
                        .amount(BigDecimal.valueOf(500))
                        .availableBalance(BigDecimal.valueOf(1000))
                        .transactionDate(now)
                        .build();

        assertEquals(response1, response2);

        assertEquals(
                response1.hashCode(),
                response2.hashCode()
        );

        assertNotNull(response1.toString());
    }
}