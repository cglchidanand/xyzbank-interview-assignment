package xyzbank.interview.assignment.entity;

import org.junit.jupiter.api.Test;

import xyzbank.interview.assignment.enums.DocumentStatus;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void shouldTestDocumentBuilderAndGetters() {

        Document document =
                Document.builder()
                        .id(1L)
                        .filePath("documents/test.pdf")
                        .documentStatus(DocumentStatus.SUCCESS)
                        .build();

        assertEquals(1L, document.getId());
        assertEquals("documents/test.pdf", document.getFilePath());
        assertEquals(DocumentStatus.SUCCESS, document.getDocumentStatus());

        assertNotNull(document);
    }
}

