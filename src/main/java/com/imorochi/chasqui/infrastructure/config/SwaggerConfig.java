package com.imorochi.chasqui.infrastructure.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import static org.springdoc.core.Constants.ALL_PATTERN;

@Profile("dev")
public class SwaggerConfig {
    @Bean
    @Profile("!prod")
    public GroupedOpenApi actuatorApi(OpenApiCustomiser actuatorOpenApiCustomiser,
                                      OperationCustomizer actuatorCustomizer,
                                      WebEndpointProperties endpointProperties) {
        return GroupedOpenApi.builder()
                .group("Actuator")
                .pathsToMatch(endpointProperties.getBasePath()+ ALL_PATTERN)
                .addOpenApiCustomiser(actuatorOpenApiCustomiser)
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Actuator API").version("1.0.1")))
                .addOperationCustomizer(actuatorCustomizer)
                .pathsToExclude("/health/*")
                .build();
    }

    @Bean
    public GroupedOpenApi usersGroup() {
        return GroupedOpenApi.builder().group("users")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                    return operation;
                })
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Users API").version("1.0.1")))
                .packagesToScan("com.imorochi.chasqui.infrastructure")
                .build();
    }

}
