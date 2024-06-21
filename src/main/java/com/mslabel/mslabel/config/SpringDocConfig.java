package com.mslabel.mslabel.config;

import java.util.Iterator;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.Components;

@Configuration
public class SpringDocConfig {
    @Bean
    public GroupedOpenApi customOpenApi() {
        return GroupedOpenApi.builder()
                .group("com.mslabel.mslabel.controller")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.info(new io.swagger.v3.oas.models.info.Info()
                .title("mslabel API Documentation")
                .description("mslabel API documentation for all available endpoints")
                .version("v1.0"));

                // Filter schemas
                filterSchemas(openApi.getComponents());
        };
    }

    private void filterSchemas(Components components) {
        Map<String, Schema> schemas = components.getSchemas();
        if (schemas != null) {
            Iterator<Map.Entry<String, Schema>> iterator = schemas.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Schema> entry = iterator.next();
                Schema<?> schema = entry.getValue();
                if (!isSchemaAnnotated(schema)) {
                    iterator.remove();
                }
            }
        }
    }

    private boolean isSchemaAnnotated(Schema<?> schema) {
        // Check if the schema description is not null
        return schema.getDescription() != null;
    }
}
