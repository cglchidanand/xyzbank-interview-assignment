package xyzbank.interview.assignment.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTypeTest {

    @Test
    void shouldTestCurrencyTypeEnum() {

        CurrencyType currencyType = CurrencyType.EUR;

        assertEquals("EUR", currencyType.name());

        assertNotNull(currencyType);
    }
}

