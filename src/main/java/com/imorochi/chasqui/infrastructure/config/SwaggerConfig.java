package com.imorochi.chasqui.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springdoc.core.Constants.ALL_PATTERN;

public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()).info(new Info().title(applicationName).description("Aplicacion de pedidos con SpringBoot")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation().description("Chasqui Delivery Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

//    @Bean
//    public GroupedOpenApi customApiV1() {
//        return GroupedOpenApi.builder()
//                .group("api")
//                .pathsToMatch("/api/**")
//                .build();
//    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder()
                .group("actuator")
                .pathsToMatch("/actuator/**")
                .build();
    }
//    @Bean
//    @Profile("!prod")
//    public GroupedOpenApi actuatorApi(OpenApiCustomiser actuatorOpenApiCustomiser,
//                                      OperationCustomizer actuatorCustomizer,
//                                      WebEndpointProperties endpointProperties) {
//        return GroupedOpenApi.builder()
//                .group("Actuator")
//                .pathsToMatch(endpointProperties.getBasePath()+ ALL_PATTERN)
//                .addOpenApiCustomiser(actuatorOpenApiCustomiser)
//                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Actuator API").description("Chasqui Delivery").version("1.0.1")))
//                .addOperationCustomizer(actuatorCustomizer)
//                .pathsToExclude("/health/*")
//                .build();
//    }

//    @Bean
//    public GroupedOpenApi usersGroup() {
//        return GroupedOpenApi.builder().group("users")
//                .addOperationCustomizer((operation, handlerMethod) -> {
//                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
//                    return operation;
//                })
//                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Chasqui Delivery").version("1.0.1")))
//                .packagesToScan("com.imorochi.chasqui.infrastructure")
//                .build();
//    }

}
