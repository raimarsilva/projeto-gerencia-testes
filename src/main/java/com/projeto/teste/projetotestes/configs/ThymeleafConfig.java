package com.projeto.teste.projetotestes.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class ThymeleafConfig {

  @Bean(name = "stringTemplateEngine")
  public TemplateEngine stringTemplateEngine() {

    SpringTemplateEngine templateEngine = new SpringTemplateEngine();

    StringTemplateResolver resolver = new StringTemplateResolver();

    resolver.setTemplateMode("HTML");

    resolver.setCacheable(false);

    templateEngine.setTemplateResolver(resolver);

    return templateEngine;
  }
}
