package com.projeto.teste.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.TestPropertySource;

import com.projeto.teste.util.EnvTestConfig;

@SpringBootTest(classes = EnvTestConfig.class)
@TestPropertySource(properties = "spring.config.import=optional:file:./.env[.properties]")
class EnvVarTest {

  @Autowired
  private ConfigurableEnvironment env;

  @Test
  @DisplayName("Deve carregar o arquivo .env e checar se as envs não estão vazias.")
  void verificarEnvsNaoVazias() {

    for (PropertySource<?> source : env.getPropertySources()) {
      if (source instanceof EnumerablePropertySource) {
        EnumerablePropertySource<?> enumerableSource = (EnumerablePropertySource<?>) source;

        if (source.getName().contains(".env")) {

          for (String pname : enumerableSource.getPropertyNames()) {
            if (pname == null)
              continue;

            String pvalue = env.getProperty(pname);

            assertNotNull(pvalue, "A env " + pname + " está nula.");
            assertFalse(pvalue.trim().isEmpty(), "A env " + pname + " está vazia.");
          }
        }
      }
    }
  }
}