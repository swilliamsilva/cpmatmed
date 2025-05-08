package com.cpmatmed.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cpmatmed.backend.repository")
public class CpmatmedBackendApplication {

    public static void main(String[] args) {
          //  Teste de variáveis de ambiente antes de subir o Spring
          System.out.println("SPRING_DATASOURCE_URL: " + System.getenv("SPRING_DATASOURCE_URL"));
          System.out.println("SPRING_DATASOURCE_USERNAME: " + System.getenv("SPRING_DATASOURCE_USERNAME"));
          System.out.println("SPRING_DATASOURCE_PASSWORD: " + System.getenv("SPRING_DATASOURCE_PASSWORD"));


        SpringApplication.run(CpmatmedBackendApplication.class, args);
    }
}