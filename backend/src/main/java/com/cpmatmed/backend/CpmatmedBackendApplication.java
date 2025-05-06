package com.cpmatmed.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cpmatmed.backend.repository")
public class CpmatmedBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CpmatmedBackendApplication.class, args);
    }
}