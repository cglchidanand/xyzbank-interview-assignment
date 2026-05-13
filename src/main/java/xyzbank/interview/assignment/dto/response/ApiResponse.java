 package xyzbank.interview.assignment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(
        name = "Generic API Response",
        description =
                "Standard response format returned by all APIs"
)

public class ApiResponse<T> {

    @Schema(
            description = "Indicates whether API call was successful",
            example = "true"
    )

    private boolean success;

    @Schema(
            description = "Response message",
            example = "Customer registered successfully"
    )

    private String message;

    @Schema(
            description = "Actual response payload"
    )

    private T data;

    public static <T> ApiResponse<T> success(
            String message,
            T data
    ) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> failure(
            String message
    ) {

        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}
 