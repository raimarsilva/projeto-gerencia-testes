package com.projeto.teste.ProjetoTestes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.SpringSecurityCoreVersion;

@SpringBootApplication
public class ProjetoTestesApplication {

	public static void main(String[] args) {
		SpringSecurityCoreVersion.getVersion();
		SpringApplication.run(ProjetoTestesApplication.class, args);
	}

}
