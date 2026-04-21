package com.projeto.teste.ProjetoTestes.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public String handle(Exception e) {

    log.error("Erro capturado: {}", e.getMessage()/* ,e */);

    log.error("Causas: {}", getCauses(e));

    return "Erro interno. Consulte o administrador.";
  }

  private List<String> getCauses(Throwable t) {
    // Throwable cause = e;

    List<String> causes = new ArrayList<>();

    while (t.getCause() != null) {
      t = t.getCause();
      causes.add(t.getMessage());
    }
    return causes;
  }
}
