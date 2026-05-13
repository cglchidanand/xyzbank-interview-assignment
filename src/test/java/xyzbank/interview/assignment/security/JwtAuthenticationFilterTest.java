package xyzbank.interview.assignment.security;

import io.jsonwebtoken.JwtException;

import jakarta.servlet.FilterChain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void shouldFilterValidTokenSuccessfully() throws Exception {

        String token = "valid-token";

        request.addHeader(
                "Authorization",
                "Bearer " + token
        );

        UserDetails userDetails =
                new User(
                        "john123",
                        "password",
                        Collections.emptyList()
                );

        when(jwtUtil.extractUsername(token))
                .thenReturn("john123");

        when(userDetailsService.loadUserByUsername("john123"))
                .thenReturn(userDetails);

        when(jwtUtil.validateToken(token, userDetails))
                .thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        verify(filterChain, times(1))
                .doFilter(request, response);
    }

    @Test
    void shouldReturnUnauthorizedForInvalidToken() throws Exception {

        String token = "invalid-token";

        request.addHeader(
                "Authorization",
                "Bearer " + token
        );

        when(jwtUtil.extractUsername(token))
                .thenThrow(new JwtException("Invalid token"));

        jwtAuthenticationFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertEquals(
                401,
                response.getStatus()
        );
    }

    @Test
    void shouldContinueFilterWhenNoAuthorizationHeader()
            throws Exception {

        jwtAuthenticationFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        verify(filterChain, times(1))
                .doFilter(request, response);
    }
}

