package com.example.springboot_rest_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@Configuration
@SuppressWarnings("unused")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket (DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
        ;
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Spring Boot API",
                "Prueba de programación de una API con Spring Boot, JPA, H2 y Swagger.",
                "1.0",
                "https://terms.demo.com",
                new Contact(
                        "Juan Pablo",
                        "https://demo.com",
                        "juampa@demo.com"
                ),
                "MIT",
                "https://mit-license.org/",
                Collections.emptyList()
        );
    }
}
