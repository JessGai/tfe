package com.tfe.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi documentationApi(OpenApiCustomizer stripPrefixCustomizer) {
        return GroupedOpenApi.builder()
                .group("documentation stage api")
                .packagesToScan("com.tfe")
                .pathsToMatch("/api/stageinst/**", "/api/panier/**", "/api/stagedesc/**", "/api/parent/**", "/api/enfant/**", "/api/paiement/**", "/api/historique/**")
                .addOpenApiCustomizer(stripPrefixCustomizer)
                .build();
    }
}
