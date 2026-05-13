package xyzbank.interview.assignment.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import xyzbank.interview.assignment.dto.response.ApiResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType(
                MediaType.APPLICATION_JSON_VALUE
        );

        ApiResponse<Object> apiResponse =
                ApiResponse.builder()
                        .success(false)
                        .message(
                                "Please login or authorize to access this resource"
                        )
                        .data(null)
                        .build();

        new ObjectMapper()
                .writeValue(
                        response.getOutputStream(),
                        apiResponse
                );
    }
}