package com.projeto.teste.projetotestes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.SpringSecurityCoreVersion;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoTestesApplication {

  public static void main(String[] args) {
    SpringSecurityCoreVersion.getVersion();
    SpringApplication.run(ProjetoTestesApplication.class, args);
  }

}
