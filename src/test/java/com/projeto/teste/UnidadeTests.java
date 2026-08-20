package com.projeto.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.projeto.teste.projetotestes.model.Salary;
import com.projeto.teste.projetotestes.model.Unidade;

class UnidadeTests {
  String u = "DEPARTAMENTO DE ARTES";
  String v = "Unidade: " + u;
  String s = Unidade.DART.toString();
  String d = Unidade.DART.getDescricao();
  BigDecimal sal = BigDecimal.valueOf(1234.56);

  Salary salary = new Salary(1, true, LocalDate.of(2026, 5, 28), sal);

  @Test
  void testEnumDescription() {

    assertEquals(u, d);
    assertEquals(v, s);
  }

  void testSalaryValue() {
    assertEquals(sal, salary.getValue());
  }
}
