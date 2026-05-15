 package xyzbank.interview.assignment.dto.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawRequestTest {

    @Test
    void shouldTestGetterSetter() {

        WithdrawRequest request =
                new WithdrawRequest();

        request.setAmount(
                BigDecimal.valueOf(200)
        );

        assertEquals(
                BigDecimal.valueOf(200),
                request.getAmount()
        );
    }

    @Test
    void shouldTestBuilder() {

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(BigDecimal.valueOf(100))
                        .build();

        assertNotNull(request);
    }

    @Test
    void shouldTestEqualsHashCodeAndToString() {

        WithdrawRequest request1 =
                WithdrawRequest.builder()
                        .amount(BigDecimal.valueOf(100))
                        .build();

        WithdrawRequest request2 =
                WithdrawRequest.builder()
                        .amount(BigDecimal.valueOf(100))
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

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(null)
                        .build();

        assertNull(request.getAmount());
    }

    @Test
    void shouldAllowZeroAmountObjectCreation() {

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(BigDecimal.ZERO)
                        .build();

        assertEquals(
                BigDecimal.ZERO,
                request.getAmount()
        );
    }
}
 