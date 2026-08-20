package com.projeto.teste.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.projeto.teste.projetotestes.model.Salary;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class SalaryTest {

  Salary s1;

  @BeforeEach
  void setup() {
    s1 = new Salary();
  }

  @Test
  void shouldShowLocalDate() {

    LocalDate createdDate = LocalDate.now(ZoneId.of("America/Recife"));
    log.info("Data atual: {}", createdDate);
    System.out.println("Data atual: " + createdDate);
    assertNotNull(createdDate);
    assertNotNull(s1);

    s1.setActive(true);
    s1.setCreatedDate(LocalDate.of(2026, Month.JANUARY, 1));
    s1.setId(1);
    s1.setValue(BigDecimal.valueOf(1632.45));

    assertEquals(1, s1.getId());
    assertEquals(true, s1.isActive());
    assertEquals("01/01/2026", s1.getCreatedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    assertEquals(BigDecimal.valueOf(1632.45), s1.getValue());
  }
}
