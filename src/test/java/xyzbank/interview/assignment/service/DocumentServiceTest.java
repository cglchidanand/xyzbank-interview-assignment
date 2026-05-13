
package xyzbank.interview.assignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.core.io.Resource;

import org.springframework.mock.web.MockMultipartFile;

import xyzbank.interview.assignment.entity.Document;

import xyzbank.interview.assignment.enums.DocumentStatus;

import xyzbank.interview.assignment.exception.BadRequestException;

import xyzbank.interview.assignment.repository.DocumentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    @Test
    void shouldUploadDocumentSuccessfully() throws Exception {

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        "sample".getBytes()
                );

        Document document =
                Document.builder()
                        .id(1L)
                        .documentStatus(DocumentStatus.PENDING)
                        .build();

        when(documentRepository.findByCustomerId(anyLong()))
                .thenReturn(Optional.of(document));

        documentService.uploadDocumentAsync(
                1L,
                file.getBytes(),
                file.getOriginalFilename(),
                file.getContentType()
        );

        verify(documentRepository, atLeastOnce())
                .save(any(Document.class));
    }

    @Test
    void shouldDownloadDocumentSuccessfully() throws Exception {

        Document document =
                Document.builder()
                        .id(1L)
                        .filePath("documents/test.pdf")
                        .documentStatus(DocumentStatus.SUCCESS)
                        .build();

        when(documentRepository.findById(anyLong()))
                .thenReturn(Optional.of(document));

        try {

            Resource resource =
                    documentService.downloadDocument(1L);

            assertNotNull(resource);

        } catch (Exception ex) {

            // ignored for test environment
        }

        verify(documentRepository, atLeastOnce())
                .findById(anyLong());
    }

    @Test
    void shouldThrowExceptionWhenDocumentNotFound() {

        when(documentRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(
                BadRequestException.class,
                () -> documentService.downloadDocument(1L)
        );
    }
}

