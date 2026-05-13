package xyzbank.interview.assignment.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OverviewResponseTest {

    @Test
    void shouldTestOverviewResponseBuilderAndGetters() {

        OverviewResponse response =
                OverviewResponse.builder()
                        .accountNumber("ABNA123456789012")
                        .accountType("SAVINGS")
                        .balance(BigDecimal.valueOf(1500.75))
                        .currency("EUR")
                        .build();

        assertEquals(
                "ABNA123456789012",
                response.getAccountNumber()
        );

        assertEquals(
                "SAVINGS",
                response.getAccountType()
        );

        assertEquals(
                BigDecimal.valueOf(1500.75),
                response.getBalance()
        );

        assertEquals(
                "EUR",
                response.getCurrency()
        );

        assertNotNull(response);
    }

    @Test
    void shouldTestNoArgsConstructorAndSetters() {

        OverviewResponse response = new OverviewResponse();

        response.setAccountNumber("ACC123");
        response.setAccountType("CURRENT");
        response.setBalance(BigDecimal.valueOf(2000.00));
        response.setCurrency("EUR");

        assertEquals("ACC123", response.getAccountNumber());
        assertEquals("CURRENT", response.getAccountType());

        assertEquals(
                BigDecimal.valueOf(2000.00),
                response.getBalance()
        );

        assertEquals("EUR", response.getCurrency());
    }

    @Test
    void shouldTestAllArgsConstructor() {

        OverviewResponse response =
                new OverviewResponse(
                        "ACC999",
                        "SALARY",
                        BigDecimal.valueOf(5000.00),
                        "EUR"
                );

        assertEquals("ACC999", response.getAccountNumber());
        assertEquals("SALARY", response.getAccountType());

        assertEquals(
                BigDecimal.valueOf(5000.00),
                response.getBalance()
        );

        assertEquals("EUR", response.getCurrency());
    }
}

