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
                        .fileName("aadhaar.pdf")
                        .fileType("application/pdf")
                        .filePath("documents/test.pdf")
                        .documentStatus(DocumentStatus.SUCCESS)
                        .failureReason(null)
                        .build();

        assertEquals(1L, document.getId());

        assertEquals(
                "aadhaar.pdf",
                document.getFileName()
        );

        assertEquals(
                "application/pdf",
                document.getFileType()
        );

        assertEquals(
                "documents/test.pdf",
                document.getFilePath()
        );

        assertEquals(
                DocumentStatus.SUCCESS,
                document.getDocumentStatus()
        );

        assertNull(document.getFailureReason());

        assertNotNull(document);
    }

    @Test
    void shouldTestSetterAndGetter() {

        Document document = new Document();

        document.setId(2L);

        document.setFileName(
                "passport.png"
        );

        document.setFileType(
                "image/png"
        );

        document.setFilePath(
                "documents/sample.png"
        );

        document.setDocumentStatus(
                DocumentStatus.FAILED
        );

        document.setFailureReason(
                "Invalid file"
        );

        assertEquals(
                2L,
                document.getId()
        );

        assertEquals(
                "passport.png",
                document.getFileName()
        );

        assertEquals(
                "image/png",
                document.getFileType()
        );

        assertEquals(
                "documents/sample.png",
                document.getFilePath()
        );

        assertEquals(
                DocumentStatus.FAILED,
                document.getDocumentStatus()
        );

        assertEquals(
                "Invalid file",
                document.getFailureReason()
        );
    }

    @Test
    void shouldTestEqualsHashCodeAndToString() {

        Document document1 =
                Document.builder()
                        .id(1L)
                        .fileName("aadhaar.pdf")
                        .fileType("application/pdf")
                        .filePath("documents/test.pdf")
                        .documentStatus(DocumentStatus.SUCCESS)
                        .build();

        Document document2 =
                Document.builder()
                        .id(1L)
                        .fileName("aadhaar.pdf")
                        .fileType("application/pdf")
                        .filePath("documents/test.pdf")
                        .documentStatus(DocumentStatus.SUCCESS)
                        .build();

        assertEquals(document1, document2);

        assertEquals(
                document1.hashCode(),
                document2.hashCode()
        );

        assertNotNull(
                document1.toString()
        );
    }

    @Test
    void shouldTestNoArgsConstructor() {

        Document document =
                new Document();

        assertNotNull(document);
    }

    @Test
    void shouldTestAllArgsConstructor() {

        Document document =
                new Document(
                        1L,
                        "aadhaar.pdf",
                        "application/pdf",
                        "documents/test.pdf",
                        DocumentStatus.SUCCESS,
                        null,
                        null,
                        null
                );

        assertEquals(
                1L,
                document.getId()
        );

        assertEquals(
                "aadhaar.pdf",
                document.getFileName()
        );

        assertEquals(
                "application/pdf",
                document.getFileType()
        );

        assertEquals(
                "documents/test.pdf",
                document.getFilePath()
        );

        assertEquals(
                DocumentStatus.SUCCESS,
                document.getDocumentStatus()
        );
    }

    @Test
    void shouldAllowNullValues() {

        Document document =
                Document.builder()
                        .id(null)
                        .fileName(null)
                        .fileType(null)
                        .filePath(null)
                        .documentStatus(null)
                        .failureReason(null)
                        .build();

        assertNull(document.getId());

        assertNull(document.getFileName());

        assertNull(document.getFileType());

        assertNull(document.getFilePath());

        assertNull(document.getDocumentStatus());

        assertNull(document.getFailureReason());
    }

    @Test
    void shouldSetUploadedAtDuringPrePersist() {

        Document document =
                new Document();

        document.prePersist();

        assertNotNull(
                document.getUploadedAt()
        );
    }
}
 