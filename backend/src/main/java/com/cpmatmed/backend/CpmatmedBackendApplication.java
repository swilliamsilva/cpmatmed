package com.cpmatmed.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cpmatmed.backend.repository")
public class CpmatmedBackendApplication {

    public static void main(String[] args) {
       //  Teste de variáveis de ambiente antes de subir o Spring
        // Log das configurações críticas
        System.out.println("\n===== CONFIGURAÇÕES DA APLICAÇÃO =====");
        System.out.println("Perfis ativos: " + Arrays.toString(env.getActiveProfiles()));
        System.out.println("server.port: " + serverPort);
        System.out.println("management.endpoints.web.base-path: " + actuatorBasePath);
        System.out.println("management.endpoints.web.exposure.include: " + actuatorExposedEndpoints);
        
        // Configurações do banco de dados
        System.out.println("\n===== DATASOURCE CONFIG =====");
        System.out.println("spring.datasource.url: " + env.getProperty("spring.datasource.url"));
        System.out.println("spring.datasource.username: " + env.getProperty("spring.datasource.username"));
        
        // Configurações do Actuator
        System.out.println("\n===== ACTUATOR CONFIG =====");
        System.out.println("management.endpoint.health.show-details: " + env.getProperty("management.endpoint.health.show-details"));
        System.out.println("management.endpoint.health.enabled: " + env.getProperty("management.endpoint.health.enabled"));
          //  Teste de variáveis de ambiente antes de subir o Spring
          System.out.println("SPRING_DATASOURCE_URL: " + System.getenv("SPRING_DATASOURCE_URL"));
          System.out.println("SPRING_DATASOURCE_USERNAME: " + System.getenv("SPRING_DATASOURCE_USERNAME"));
          System.out.println("SPRING_DATASOURCE_PASSWORD: " + System.getenv("SPRING_DATASOURCE_PASSWORD"));


        SpringApplication.run(CpmatmedBackendApplication.class, args);
    }
}