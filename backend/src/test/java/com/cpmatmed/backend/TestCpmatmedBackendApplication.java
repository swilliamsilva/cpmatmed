package com.cpmatmed.backend;

import org.springframework.boot.SpringApplication;

public class TestCpmatmedBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(CpmatmedBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
