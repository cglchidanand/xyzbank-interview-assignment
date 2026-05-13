 
package xyzbank.interview.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import xyzbank.interview.assignment.dto.response.OverviewResponse;
import xyzbank.interview.assignment.service.CustomerService;

@RestController
@RequiredArgsConstructor

@Tag(
        name = "Customer APIs",
        description = "APIs for customer account operations"
)

public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Get Account Overview",
            description =
                    "Returns customer account details including "
                            + "account number, balance, currency "
                            + "and account type."
    )

    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Overview fetched successfully"
            ),

            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = @Content
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Customer account not found",
                    content = @Content
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })

    @GetMapping("/overview")

    public ResponseEntity<
            xyzbank.interview.assignment.dto.response.ApiResponse<OverviewResponse>
            > overview() {

        OverviewResponse response =
                customerService.getOverview();

        return ResponseEntity.ok(

                xyzbank.interview.assignment.dto.response.ApiResponse
                        .<OverviewResponse>builder()

                        .success(true)
                        .message("Account overview fetched successfully")
                        .data(response)
                        .build()
        );
    }
}

