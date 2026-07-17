package com.projeto.teste.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SalaryTest {

  @Test
  void shouldShowLocalDate() {

    LocalDate createdDate = LocalDate.now(ZoneId.of("America/Recife"));
    log.info("Data atual: {}", createdDate);
    System.out.println("Data atual: {}" + createdDate);
    assertNotNull(createdDate);
  }
}
