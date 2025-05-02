// CpmatmedBackendApplication.java - Aplicação: cpmatmed
package com.cpmatmed.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cpmatmed.backend.repository")
public class CpmatmedBackendApplication {
    public static void main(String[] args) {
        // Carrega as variáveis do .env (se houver)
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(CpmatmedBackendApplication.class, args);
    }
}
