package xyzbank.interview.assignment.security;

import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

class JwtAuthenticationEntryPointTest {

    @Test
    void shouldHandleUnauthorizedAccess() throws Exception {

        JwtAuthenticationEntryPoint entryPoint =
                new JwtAuthenticationEntryPoint();

        MockHttpServletRequest request =
                new MockHttpServletRequest();

        MockHttpServletResponse response =
                new MockHttpServletResponse();

        entryPoint.commence(
                request,
                response,
                new BadCredentialsException("Unauthorized")
        );

        assertEquals(
                HttpServletResponse.SC_UNAUTHORIZED,
                response.getStatus()
        );

        assertEquals(
                "application/json",
                response.getContentType()
        );

        String content =
                response.getContentAsString();

        assertTrue(
                content.contains(
                        "Please login or authorize to access this resource"
                )
        );
    }
}

