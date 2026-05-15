 package xyzbank.interview.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.test.web.servlet.MockMvc;

import xyzbank.interview.assignment.dto.request.DepositRequest;
import xyzbank.interview.assignment.dto.request.WithdrawRequest;

import xyzbank.interview.assignment.dto.response.TransactionResponse;

import xyzbank.interview.assignment.exception.GlobalExceptionHandler;

import xyzbank.interview.assignment.security.JwtAuthenticationFilter;
import xyzbank.interview.assignment.security.JwtUtil;

import xyzbank.interview.assignment.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)

@AutoConfigureMockMvc(addFilters = false)

@Import(GlobalExceptionHandler.class)

class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void shouldDepositSuccessfully() throws Exception {

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.valueOf(1000))
                        .build();

        TransactionResponse response =
                TransactionResponse.builder()
                        .transactionType("DEPOSIT")
                        .amount(BigDecimal.valueOf(1000))
                        .availableBalance(BigDecimal.valueOf(6000))
                        .transactionDate(LocalDateTime.now())
                        .build();

        when(transactionService.deposit(any()))
                .thenReturn(response);

        mockMvc.perform(
                        post("/transactions/deposit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                                .with(
                                        authentication(
                                                new UsernamePasswordAuthenticationToken(
                                                        "john123",
                                                        null
                                                )
                                        )
                                )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldFailDepositForInvalidAmount() throws Exception {

        DepositRequest request =
                DepositRequest.builder()
                        .amount(BigDecimal.ZERO)
                        .build();

        mockMvc.perform(
                        post("/transactions/deposit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                                .with(
                                        authentication(
                                                new UsernamePasswordAuthenticationToken(
                                                        "john123",
                                                        null
                                                )
                                        )
                                )
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldWithdrawSuccessfully() throws Exception {

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(BigDecimal.valueOf(500))
                        .build();

        TransactionResponse response =
                TransactionResponse.builder()
                        .transactionType("WITHDRAW")
                        .amount(BigDecimal.valueOf(500))
                        .availableBalance(BigDecimal.valueOf(4500))
                        .transactionDate(LocalDateTime.now())
                        .build();

        when(transactionService.withdraw(any()))
                .thenReturn(response);

        mockMvc.perform(
                        post("/transactions/withdraw")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                                .with(
                                        authentication(
                                                new UsernamePasswordAuthenticationToken(
                                                        "john123",
                                                        null
                                                )
                                        )
                                )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldFailWithdrawForInvalidAmount() throws Exception {

        WithdrawRequest request =
                WithdrawRequest.builder()
                        .amount(BigDecimal.ZERO)
                        .build();

        mockMvc.perform(
                        post("/transactions/withdraw")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                                .with(
                                        authentication(
                                                new UsernamePasswordAuthenticationToken(
                                                        "john123",
                                                        null
                                                )
                                        )
                                )
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnTransactionHistory() throws Exception {

        when(transactionService.getTransactions())
                .thenReturn(List.of());

        mockMvc.perform(
                        get("/transactions/history")
                                .with(
                                        authentication(
                                                new UsernamePasswordAuthenticationToken(
                                                        "john123",
                                                        null
                                                )
                                        )
                                )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}

