package com.cpmatmed.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cpmatmed.backend.repository")
public class CpmatmedBackendApplication {

    @Autowired
    private Environment env;

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${management.endpoints.web.base-path:/actuator}")
    private String actuatorBasePath;

    @Value("${management.endpoints.web.exposure.include:*}")
    private String actuatorExposedEndpoints;

    public static void main(String[] args) {
        // Log de TODAS as variáveis de ambiente
        System.out.println("\n===== VARIÁVEIS DE AMBIENTE =====");
        Map<String, String> envVariables = System.getenv();
        envVariables.forEach((key, value) -> System.out.println(key + ": " + value));

        SpringApplication.run(CpmatmedBackendApplication.class, args);
    }

    @PostConstruct
    public void logConfigurations() {
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
    }
}