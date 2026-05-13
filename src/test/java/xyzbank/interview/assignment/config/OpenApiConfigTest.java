package xyzbank.interview.assignment.config;

import io.swagger.v3.oas.models.OpenAPI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiConfigTest {

    @Test
    void shouldCreateOpenApiSuccessfully() {

        OpenApiConfig openApiConfig =
                new OpenApiConfig();

        OpenAPI openAPI =
                openApiConfig.customOpenAPI();

        assertNotNull(openAPI);

        assertNotNull(openAPI.getInfo());

        assertEquals(
                "XYZ Bank Digital Banking API",
                openAPI.getInfo().getTitle()
        );

        assertEquals(
                "1.0",
                openAPI.getInfo().getVersion()
        );

        assertNotNull(
                openAPI.getComponents()
        );

        assertTrue(
                openAPI.getComponents()
                        .getSecuritySchemes()
                        .containsKey("bearerAuth")
        );
    }
}
