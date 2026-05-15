 package xyzbank.interview.assignment.dto.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DepositRequestTest {

    @Test
    void shouldTestGetterSetter() {

        DepositRequest request =
                new DepositRequest();

        request.setAmount(
                BigDecimal.valueOf(1000)
        );

        assertEquals(
                BigDecimal.valueOf(1000),
                request.getAmount()
        );
    }

    @Test
    void shouldTestBuilder() {

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.valueOf(500))
                        .build();

        assertNotNull(request);
    }

    @Test
    void shouldTestEqualsHashCodeAndToString() {

        DepositRequest request1 =
                DepositRequest.builder()
                        .amount(BigDecimal.valueOf(500))
                        .build();

        DepositRequest request2 =
                DepositRequest.builder()
                        .amount(BigDecimal.valueOf(500))
                        .build();

        assertEquals(request1, request2);

        assertEquals(
                request1.hashCode(),
                request2.hashCode()
        );

        assertNotNull(request1.toString());
    }

    @Test
    void shouldAllowNullAmount() {

        DepositRequest request =
                DepositRequest.builder()
                        .amount(null)
                        .build();

        assertNull(request.getAmount());
    }

    @Test
    void shouldAllowZeroAmountObjectCreation() {

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.ZERO)
                        .build();

        assertEquals(
                BigDecimal.ZERO,
                request.getAmount()
        );
    }
}
 