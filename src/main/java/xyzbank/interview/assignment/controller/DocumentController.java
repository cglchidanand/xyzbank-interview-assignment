package xyzbank.interview.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import xyzbank.interview.assignment.service.DocumentService;

@RestController
@RequiredArgsConstructor

@RequestMapping("/documents")

@Tag(
name = "Document APIs",
description = "APIs for uploading and downloading customer ID documents"
)

public class DocumentController {


private final DocumentService documentService;

@Operation(
        summary = "Upload customer ID document",
        description =
                "Uploads customer identification document. "
                        + "Supported formats are PDF, JPG, JPEG and PNG. "
                        + "Maximum allowed file size is 2MB."
)

@ApiResponses(value = {

        @ApiResponse(
                responseCode = "200",
                description = "Document upload started successfully"
        ),

        @ApiResponse(
                responseCode = "400",
                description = "Invalid file or upload failed",
                content = @Content
        ),

        @ApiResponse(
                responseCode = "401",
                description = "Unauthorized access",
                content = @Content
        ),

        @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content
        )
})

@PostMapping(
        value = "/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)

public ResponseEntity<
        xyzbank.interview.assignment.dto.response.ApiResponse<String>
        > uploadDocument(

        @RequestParam("file")
        MultipartFile file,

        Authentication authentication
) {

    try {

        String username =
                authentication != null
                        ? authentication.getName()
                        : "john123";

        Long customerId =
                documentService
                        .getCustomerIdByUsername(
                                username
                        );

        byte[] fileBytes =
                file.getBytes();

        documentService.uploadDocumentAsync(
                customerId,
                fileBytes,
                file.getOriginalFilename(),
                file.getContentType()
        );

        return ResponseEntity.ok(

                xyzbank.interview.assignment.dto.response.ApiResponse
                        .<String>builder()

                        .success(true)
                        .message("Document upload started")
                        .data("PENDING")
                        .build()
        );

    } catch (Exception ex) {

        return ResponseEntity.badRequest().body(

                xyzbank.interview.assignment.dto.response.ApiResponse
                        .<String>builder()

                        .success(false)
                        .message("File upload failed")
                        .data(ex.getMessage())
                        .build()
        );
    }
}

@Operation(
        summary = "Download customer document",
        description =
                "Downloads the logged-in customer's uploaded ID document."
)

@ApiResponses(value = {

        @ApiResponse(
                responseCode = "200",
                description = "Document downloaded successfully"
        ),

        @ApiResponse(
                responseCode = "401",
                description = "Unauthorized access",
                content = @Content
        ),

        @ApiResponse(
                responseCode = "404",
                description = "Document not found",
                content = @Content
        ),

        @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content
        )
})

@GetMapping("/download")

public ResponseEntity<Resource> downloadDocument(

        Authentication authentication
) {

    String username =
            authentication != null
                    ? authentication.getName()
                    : "john123";

    Resource resource =
            documentService.downloadDocument(
                    username
            );

    return ResponseEntity.ok()

            .contentType(
                    MediaType.APPLICATION_OCTET_STREAM
            )

            .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\""
                            + resource.getFilename()
                            + "\""
            )

            .body(resource);
}


}
