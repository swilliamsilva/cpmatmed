package com.cpmatmed.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cpmatmed.backend.repository")
public class CpmatmedBackendApplication {

    public static void main(String[] args) {
        // Log de variáveis de ambiente ANTES do Spring iniciar
        System.out.println("\n===== VARIÁVEIS DE AMBIENTE =====");
        Map<String, String> envVariables = System.getenv();
        envVariables.forEach((key, value) -> System.out.println(key + ": " + value));

        SpringApplication app = new SpringApplication(CpmatmedBackendApplication.class);
        
        // Adicione um listener para capturar o ambiente assim que ele estiver pronto
        app.addListeners((ApplicationListener<ApplicationEnvironmentPreparedEvent>) event -> {
            ConfigurableEnvironment environment = event.getEnvironment();
            
            // Log das configurações do Spring
            System.out.println("\n===== CONFIGURAÇÕES DA APLICAÇÃO =====");
            System.out.println("Perfis ativos: " + Arrays.toString(environment.getActiveProfiles()));
            System.out.println("server.port: " + environment.getProperty("server.port"));
            System.out.println("management.endpoints.web.base-path: " + environment.getProperty("management.endpoints.web.base-path"));
            System.out.println("management.endpoints.web.exposure.include: " + environment.getProperty("management.endpoints.web.exposure.include"));
            
            System.out.println("\n===== DATASOURCE CONFIG =====");
            System.out.println("spring.datasource.url: " + environment.getProperty("spring.datasource.url"));
            System.out.println("spring.datasource.username: " + environment.getProperty("spring.datasource.username"));
            
            System.out.println("\n===== ACTUATOR CONFIG =====");
            System.out.println("management.endpoint.health.show-details: " + environment.getProperty("management.endpoint.health.show-details"));
            System.out.println("management.endpoint.health.enabled: " + environment.getProperty("management.endpoint.health.enabled"));
        });
        
        app.run(args);
    }
}