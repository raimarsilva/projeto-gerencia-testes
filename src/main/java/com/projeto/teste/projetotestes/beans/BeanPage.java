package com.projeto.teste.projetotestes.beans;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;
import lombok.Setter;

@Component
@RequestScope
@Getter
@Setter
public class BeanPage {
  private String title;
  private String content;

  public BeanPage() {
    title = "titulo iunstanciado do bean!";
  }
}
