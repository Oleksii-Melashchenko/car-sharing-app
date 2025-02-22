package com.clozex.carsharingapp.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    private static final String SECURITY_SCHEME_NAME = "BearerAuth";
    private static final String SCHEME_TYPE = "bearer";
    private static final String FORMAT = "JWT";

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME,
                    new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme(SCHEME_TYPE)
            .bearerFormat(FORMAT)))
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
    }
}
