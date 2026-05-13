 package xyzbank.interview.assignment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter

@Schema(
        name = "Upload Document Request",
        description =
                "Request object used for uploading customer ID document"
)

public class UploadDocumentRequest {

    @Schema(
            description =
                    "Customer ID document file. "
                            + "Supported formats: PDF, JPG, JPEG, PNG. "
                            + "Maximum size allowed is 2MB."
    )

    private MultipartFile file;
}
 