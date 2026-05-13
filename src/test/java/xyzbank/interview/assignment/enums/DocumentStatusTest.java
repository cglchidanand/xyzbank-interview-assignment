package xyzbank.interview.assignment.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentStatusTest {

    @Test
    void shouldTestDocumentStatusEnum() {

        DocumentStatus pending = DocumentStatus.PENDING;
        DocumentStatus success = DocumentStatus.SUCCESS;
        DocumentStatus failed = DocumentStatus.FAILED;

        assertEquals("PENDING", pending.name());
        assertEquals("SUCCESS", success.name());
        assertEquals("FAILED", failed.name());

        assertNotNull(pending);
        assertNotNull(success);
        assertNotNull(failed);
    }
}

