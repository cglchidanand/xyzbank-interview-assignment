package xyzbank.interview.assignment.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void shouldCreatePasswordEncoderSuccessfully() {

        SecurityConfig securityConfig =
                new SecurityConfig(
                        jwtAuthenticationFilter,
                        jwtAuthenticationEntryPoint
                );

        PasswordEncoder passwordEncoder =
                securityConfig.passwordEncoder();

        assertNotNull(passwordEncoder);

        String encodedPassword =
                passwordEncoder.encode("password");

        assertTrue(
                passwordEncoder.matches(
                        "password",
                        encodedPassword
                )
        );
    }

    @Test
    void shouldCreateAuthenticationManagerSuccessfully()
            throws Exception {

        when(authenticationConfiguration.getAuthenticationManager())
                .thenReturn(authenticationManager);

        SecurityConfig securityConfig =
                new SecurityConfig(
                        jwtAuthenticationFilter,
                        jwtAuthenticationEntryPoint
                );

        AuthenticationManager manager =
                securityConfig.authenticationManager(
                        authenticationConfiguration
                );

        assertNotNull(manager);

        verify(authenticationConfiguration, times(1))
                .getAuthenticationManager();
    }
}

