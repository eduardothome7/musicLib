package com.example.musicLib.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todos os endpoints
            .allowedOrigins("http://localhost:3000")  // Permite o frontend no localhost:3000
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
            .allowedHeaders("Content-Type", "Token")  // Cabeçalhos permitidos
            .allowCredentials(true);  // Permite credenciais como cookies ou tokens
    }
}