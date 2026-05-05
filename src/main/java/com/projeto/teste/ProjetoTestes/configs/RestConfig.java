package com.projeto.teste.ProjetoTestes.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class RestConfig {
  @Bean
  public RestTemplate restTemplate() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(5000);
    factory.setReadTimeout(5000);
    return new RestTemplate(factory);
  }

  @Bean(name = "stringTemplateEngine")
  public TemplateEngine stringTemplateEngine() {
    TemplateEngine templateEngine = new TemplateEngine();
    StringTemplateResolver resolver = new StringTemplateResolver();
    templateEngine.setTemplateResolver(resolver);
    return templateEngine;
  }
}
