package xyzbank.interview.assignment.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {

        jwtUtil = new JwtUtil();

        ReflectionTestUtils.setField(
                jwtUtil,
                "secret",
                "mysecretkeymysecretkeymysecretkey12"
        );

        ReflectionTestUtils.setField(
                jwtUtil,
                "expiration",
                86400000L
        );
    }
     		@Test
    		void shouldFailWhenInvalidToken() {

    		    String token =
    		            jwtUtil.generateToken("john123");

    		    UserDetails userDetails =
    		            User.builder()
    		                    .username("differentUser")
    		                    .password("password")
    		                    .authorities("USER")
    		                    .build();

    		    boolean valid =
    		            jwtUtil.validateToken(token, userDetails);

    		    assertFalse(valid);
    		}
    		

    @Test
    void shouldGenerateAndValidateToken() {

        String token =
                jwtUtil.generateToken("john123");

        UserDetails userDetails =
                User.builder()
                        .username("john123")
                        .password("password")
                        .authorities("USER")
                        .build();

        assertNotNull(token);

        assertTrue(
                jwtUtil.validateToken(token, userDetails)
        );
    }

    @Test
    void shouldExtractUsername() {

        String token =
                jwtUtil.generateToken("john123");

        String username =
                jwtUtil.extractUsername(token);

        assertEquals("john123", username);
    }
}