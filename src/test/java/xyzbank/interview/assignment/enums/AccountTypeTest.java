package xyzbank.interview.assignment.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTypeTest {

    @Test
    void shouldTestAccountTypeEnum() {

        AccountType savings = AccountType.SAVINGS;
        AccountType salary = AccountType.SALARY;
        AccountType current = AccountType.CURRENT;

        assertEquals("SAVINGS", savings.name());
        assertEquals("SALARY", salary.name());
        assertEquals("CURRENT", current.name());

        assertNotNull(savings);
        assertNotNull(salary);
        assertNotNull(current);
    }
}

