package br.com.pedrofurtadoflores.portfoliocrudapi.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Portfolio Spring Boot CRUD")
                        .description("API REST para gerenciamento de produtos.")
                        .version("1.0.0"));
    }
}