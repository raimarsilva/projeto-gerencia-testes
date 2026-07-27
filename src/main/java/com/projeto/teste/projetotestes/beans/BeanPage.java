package com.projeto.teste.projetotestes.beans;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
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
