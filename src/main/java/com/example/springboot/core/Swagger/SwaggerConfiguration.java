package com.example.springboot.core.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot REST API")
                        .description("Spring Boot REST API with Springdoc OpenAPI 3")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("John Doe")
                                .email("test@gamil.com")
                                .url("http://www.example.com"))
                )
                .servers(Collections.singletonList(new Server().
                        url("http://localhost:8080")
                        .description("Local Server"))
                );
    }
}