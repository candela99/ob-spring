package com.example.obrestdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

/*
* Acá vamos a crear un Bean (SwaggerConfig) para que SpringBoot sea capaz de
* detectarlo e inicialiar Swagger.
* Usamos Swagger para documentar la API REST, de forma dinamica:
* si se produce un cambio en la API, estos cambios se verán reflejados en la documentación
 *
* */
@Configuration
public class SwaggerConfig {
    /*
    * Docket está pensado para ser la interfaz principal
    * */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build(); /*Se construyen todas las llamadas anteriores a funciones (Programación Funcional)*/
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Spring Boot Books API REST",
                "Library API REST docs",
                "1.0",
                "http://www.google.com",
                new Contact("Candela", "http://www.google.com", "candela@example.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList()
        );
    }
}
