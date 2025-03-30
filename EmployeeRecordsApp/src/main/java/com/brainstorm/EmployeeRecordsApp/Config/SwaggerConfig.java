package com.brainstorm.EmployeeRecordsApp.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management System Spring Boot Application")
                        .version("4.0")
                        .description("CRUD Operations are performed on Employee Records stored in Postgresql using Rest APIs"));
    }
}
