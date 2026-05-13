 package xyzbank.interview.assignment.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.Components;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName =
                "bearerAuth";

        return new OpenAPI()

                .info(
                        new Info()

                                .title(
                                        "XYZ Bank Digital Banking API"
                                )

                                .version("1.0")

                                .description(
                                        """
                                        REST APIs for XYZ Bank digital banking system.

                                        Features:
                                        - Customer registration
                                        - JWT authentication
                                        - Account overview
                                        - Document upload/download
                                        - Banking validations

                                        Security:
                                        Protected APIs require JWT Bearer Token.
                                        """
                                )

                                .contact(
                                        new Contact()
                                                .name("XYZ Bank")
                                                .email("support@xyzbank.com")
                                )
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName)
                )

                .components(
                        new Components()

                                .addSecuritySchemes(
                                        securitySchemeName,

                                        new SecurityScheme()

                                                .name(securitySchemeName)

                                                .type(
                                                        SecurityScheme.Type.HTTP
                                                )

                                                .scheme("bearer")

                                                .bearerFormat("JWT")

                                                .description(
                                                        "Enter JWT Bearer token"
                                                )
                                )
                );
    }
}
 