package com.projeto.teste.ProjetoTestes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handle(Exception ex) {

        log.error("Erro capturado: {}", ex.getMessage());

        return "Erro interno. Consulte o administrador.";
    }
}

