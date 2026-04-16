package com.ProjetoParte1.eventos_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Eventos") // 👈 TROCA AQUI
                        .version("1.0")
                        .description("Sistema de gerenciamento de Eventos, Usuários, Inscrições, Categorias e Endereços."));
    }
}