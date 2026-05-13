package xyzbank.interview.assignment.dto.request;

import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class UploadDocumentRequestTest {

    @Test
    void shouldTestSettersAndGetters() {

        UploadDocumentRequest request =
                new UploadDocumentRequest();

        MultipartFile file =
                new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        "sample".getBytes()
                );

        request.setFile(file);

        assertNotNull(request.getFile());

        assertEquals(
                "test.pdf",
                request.getFile().getOriginalFilename()
        );
    }

    @Test
    void shouldTestToString() {

        UploadDocumentRequest request =
                new UploadDocumentRequest();

        String result =
                request.toString();

        assertNotNull(result);
    }
}

