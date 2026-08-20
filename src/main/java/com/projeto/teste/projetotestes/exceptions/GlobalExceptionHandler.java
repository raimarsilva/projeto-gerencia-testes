package com.projeto.teste.projetotestes.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<Void> handleNoResourceFound(NoResourceFoundException ex) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(Exception.class)
  public String handle(Exception e) {

    log.error("Erro capturado: {}", e.getMessage(), e);

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
