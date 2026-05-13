
package xyzbank.interview.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.mock.web.MockMultipartFile;

import org.springframework.test.web.servlet.MockMvc;

import xyzbank.interview.assignment.dto.request.LoginRequest;
import xyzbank.interview.assignment.dto.response.LoginResponse;
import xyzbank.interview.assignment.dto.response.RegisterResponse;

import xyzbank.interview.assignment.exception.GlobalExceptionHandler;

import xyzbank.interview.assignment.security.JwtAuthenticationFilter;
import xyzbank.interview.assignment.security.JwtUtil;

import xyzbank.interview.assignment.service.AuthService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)

@AutoConfigureMockMvc(addFilters = false)

@Import(GlobalExceptionHandler.class)

class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void shouldRegisterSuccessfully() throws Exception {

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        "sample".getBytes()
                );

        RegisterResponse response =
                RegisterResponse.builder()
                        .username("john123")
                        .generatedPassword("Pass@123")
                        .accountNumber("ABNA123456789012")
                        .documentStatus("PENDING")
                        .build();

        when(authService.register(any(), any()))
                .thenReturn(response);

        mockMvc.perform(
                        multipart("/register")
                                .file(file)
                                .param("fullName", "John Doe")
                                .param("email", "john@test.com")
                                .param("country", "Netherlands")
                                .param("dateOfBirth", "1990-01-01")
                                .param("accountType", "savings")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldRejectInvalidAccountType() throws Exception {

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        "test.pdf",
                        "application/pdf",
                        "sample".getBytes()
                );

        mockMvc.perform(
                        multipart("/register")
                                .file(file)
                                .param("fullName", "John Doe")
                                .param("email", "john@test.com")
                                .param("country", "Netherlands")
                                .param("dateOfBirth", "1990-01-01")
                                .param("accountType", "invalid")
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {

        LoginRequest request =
                LoginRequest.builder()
                        .username("john123")
                        .password("password")
                        .build();

        LoginResponse response =
                LoginResponse.builder()
                        .username("john123")
                        .token("jwt-token")
                        .build();

        when(authService.login(any(LoginRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/logon")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(
                                        objectMapper.writeValueAsString(
                                                request
                                        )
                                )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldRejectInvalidLoginRequest() throws Exception {

        String invalidRequest =
                """
                {
                    "username": "",
                    "password": ""
                }
                """;

        mockMvc.perform(
                        post("/logon")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(invalidRequest)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

