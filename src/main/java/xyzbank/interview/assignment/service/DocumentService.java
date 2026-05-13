package xyzbank.interview.assignment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyzbank.interview.assignment.entity.Customer;
import xyzbank.interview.assignment.entity.Document;
import xyzbank.interview.assignment.enums.DocumentStatus;
import xyzbank.interview.assignment.exception.BadRequestException;
import xyzbank.interview.assignment.repository.CustomerRepository;
import xyzbank.interview.assignment.repository.DocumentRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final DocumentRepository documentRepository;

    private final CustomerRepository customerRepository;

    @Async
    public void uploadDocumentAsync(

            Long customerId,

            byte[] fileBytes,

            String originalFilename,

            String contentType
    ) {

        try {

            Customer customer =
                    customerRepository
                            .findById(customerId)
                            .orElseThrow(() ->
                                    new BadRequestException(
                                            "Customer not found"
                                    )
                            );

            Document document =
                    documentRepository
                            .findByCustomerId(customer.getId())
                            .orElseThrow(() ->
                                    new BadRequestException(
                                            "Document not found"
                                    )
                            );

            // File format validation
            if (
                    !contentType.equals("application/pdf")
                    && !contentType.equals("image/jpeg")
                    && !contentType.equals("image/png")
            ) {

                throw new BadRequestException(
                        "Only PDF, JPG and PNG files are allowed"
                );
            }

            // File size validation (2 MB)
            long maxSize = 2 * 1024 * 1024;

            if (fileBytes.length > maxSize) {

                throw new BadRequestException(
                        "File size should not exceed 2 MB"
                );
            }

            // Create documents folder
            File uploadFolder =
                    new File("documents");

            if (!uploadFolder.exists()) {

                uploadFolder.mkdirs();
            }

            // Unique file name
            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + originalFilename;

            // Final destination
            File destination =
                    new File(uploadFolder, fileName);

            // Save file
            FileOutputStream fos =
                    new FileOutputStream(destination);

            fos.write(fileBytes);

            fos.close();

            // Update DB
            document.setFileName(
                    originalFilename
            );

            document.setFileType(
                    contentType
            );

            document.setFilePath(
                    destination.getAbsolutePath()
            );

            document.setDocumentStatus(
                    DocumentStatus.SUCCESS
            );

            document.setFailureReason(null);

            documentRepository.save(document);

            log.info(
                    "Document uploaded successfully for customer id {}",
                    customerId
            );

        } catch (Exception ex) {

            log.error(
                    "File upload failed",
                    ex
            );

            updateFailedStatus(
                    customerId,
                    ex.getMessage()
            );
        }
    }

    public Long getCustomerIdByUsername(
            String username
    ) {

        Customer customer =
                customerRepository
                        .findByUsername(username)
                        .orElseThrow(() ->
                                new BadRequestException(
                                        "Customer not found"
                                )
                        );

        return customer.getId();
    }

    public Resource downloadDocument(
            String username
    ) {

        try {

            Customer customer =
                    customerRepository
                            .findByUsername(username)
                            .orElseThrow(() ->
                                    new BadRequestException(
                                            "Customer not found"
                                    )
                            );

            Document document =
                    documentRepository
                            .findByCustomerId(customer.getId())
                            .orElseThrow(() ->
                                    new BadRequestException(
                                            "Document not found"
                                    )
                            );

            // Check whether file was uploaded
            if (
                    document.getFilePath() == null
                    || document.getFilePath().isBlank()
            ) {

                throw new BadRequestException(
                        "No document uploaded yet. Please upload a document first."
                );
            }

            Path path =
                    Paths.get(
                            document.getFilePath()
                    );

            Resource resource =
                    new UrlResource(path.toUri());

            if (!resource.exists()) {

                throw new BadRequestException(
                        "File not found"
                );
            }

            return resource;

        } catch (MalformedURLException ex) {

            throw new BadRequestException(
                    "Unable to download document"
            );
        }
    }

    public Resource downloadDocument(
            Long documentId
    ) {

        try {

            Document document =
                    documentRepository
                            .findById(documentId)
                            .orElseThrow(() ->
                                    new BadRequestException(
                                            "Document not found"
                                    )
                            );

            // Check whether file was uploaded
            if (
                    document.getFilePath() == null
                    || document.getFilePath().isBlank()
            ) {

                throw new BadRequestException(
                        "No document uploaded yet. Please upload a document first."
                );
            }

            Path path =
                    Paths.get(
                            document.getFilePath()
                    );

            Resource resource =
                    new UrlResource(
                            path.toUri()
                    );

            if (!resource.exists()) {

                throw new BadRequestException(
                        "File not found"
                );
            }

            return resource;

        } catch (MalformedURLException ex) {

            throw new BadRequestException(
                    "Unable to download document"
            );
        }
    }

    public Document getDocumentByCustomerId(
            Long customerId
    ) {

        return documentRepository
                .findByCustomerId(customerId)
                .orElseThrow(() ->
                        new BadRequestException(
                                "Document not found"
                        )
                );
    }

    private void updateFailedStatus(

            Long customerId,

            String reason
    ) {

        Document document =
                documentRepository
                        .findByCustomerId(customerId)
                        .orElse(null);

        if (document != null) {

            document.setDocumentStatus(
                    DocumentStatus.FAILED
            );

            document.setFailureReason(reason);

            documentRepository.save(document);
        }
    }
}