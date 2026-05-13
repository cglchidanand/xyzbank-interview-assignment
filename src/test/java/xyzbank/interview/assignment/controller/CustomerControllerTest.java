
package xyzbank.interview.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.test.web.servlet.MockMvc;

import xyzbank.interview.assignment.dto.response.OverviewResponse;

import xyzbank.interview.assignment.exception.GlobalExceptionHandler;

import xyzbank.interview.assignment.security.JwtAuthenticationFilter;
import xyzbank.interview.assignment.security.JwtUtil;

import xyzbank.interview.assignment.service.CustomerService;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)

@AutoConfigureMockMvc(addFilters = false)

@Import(GlobalExceptionHandler.class)

class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void shouldReturnOverviewSuccessfully() throws Exception {

        OverviewResponse response =
                OverviewResponse.builder()
                        .accountNumber("ABNA123456789012")
                        .accountType("SAVINGS")
                        .currency("EUR")
                        .balance(BigDecimal.valueOf(5000))
                        .build();

        when(customerService.getOverview())
                .thenReturn(response);

        mockMvc.perform(get("/overview"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnInternalServerError() throws Exception {

        when(customerService.getOverview())
                .thenThrow(
                        new RuntimeException("Error")
                );

        mockMvc.perform(get("/overview"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}

