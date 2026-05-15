 package xyzbank.interview.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import xyzbank.interview.assignment.dto.request.DepositRequest;
import xyzbank.interview.assignment.dto.request.WithdrawRequest;

import xyzbank.interview.assignment.dto.response.ApiResponse;
import xyzbank.interview.assignment.dto.response.TransactionResponse;

import xyzbank.interview.assignment.entity.Transaction;

import xyzbank.interview.assignment.service.TransactionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")

@Tag(
        name = "Transaction APIs",
        description = "APIs for deposit, withdraw and transaction history"
)

public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")

    @Operation(
            summary = "Deposit Amount",
            description = "Authenticated customer can deposit money into account"
    )

    public ResponseEntity<
            ApiResponse<TransactionResponse>
            > deposit(

            @Valid
            @RequestBody
            DepositRequest request
    ) {

        TransactionResponse response =
                transactionService.deposit(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Amount deposited successfully",
                        response
                )
        );
    }

    @PostMapping("/withdraw")

    @Operation(
            summary = "Withdraw Amount",
            description = "Authenticated customer can withdraw money from account"
    )

    public ResponseEntity<
            ApiResponse<TransactionResponse>
            > withdraw(

            @Valid
            @RequestBody
            WithdrawRequest request
    ) {

        TransactionResponse response =
                transactionService.withdraw(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Amount withdrawn successfully",
                        response
                )
        );
    }

    @GetMapping("/history")

    @Operation(
            summary = "Transaction History",
            description = "Fetch logged-in customer transaction history"
    )

    public ResponseEntity<
            ApiResponse<List<Transaction>>
            > history() {

        List<Transaction> transactions =
                transactionService.getTransactions();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Transaction history fetched successfully",
                        transactions
                )
        );
    }
}
 