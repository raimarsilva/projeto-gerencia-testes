package com.projeto.teste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.projeto.teste.ProjetoTestes.model.Contrato;
import com.projeto.teste.ProjetoTestes.repository.ContratoRepository;
import com.projeto.teste.ProjetoTestes.service.ContratoService;

@ExtendWith(SpringExtension.class)
public class ContratoServiceTests {

  @InjectMocks
  ContratoService contratoService;

  @Mock
  ContratoRepository contratoRepository;

  Contrato ct;

  @BeforeEach
  public void setUp() {
    ct = new Contrato();
    ct.setNumProc("12345");
    ct.setAnoContrato(2023);
    ct.setNome("Contrato Teste");
    ct.setEdital("Edital Teste");
    ct.setDouEdital("DOU Teste");
    ct.setDataDouEdital(LocalDate.of(2023, 10, 1));
    ct.setDataAssinatura(LocalDate.of(2023, 10, 1));
    ct.setRemuneracao(BigDecimal.valueOf(5432.19));

    when(contratoRepository.save(org.mockito.ArgumentMatchers.any(Contrato.class)))
      .thenAnswer(invoc -> invoc.getArgument(0));
  }

  @Test
  void testSalvarContrato() {

    Contrato cts = contratoService.salvar(ct);
    System.out.println("data assin extenso: " + cts.getDataAssExtenso());
    System.out.println("remun extenso: " + cts.getRemuneracaoExtenso());

    // Assertions para verificar se o contrato foi salvo corretamente
    assertNotNull(cts);
    assertEquals("01 de outubro de 2023", cts.getDataAssExtenso());
    assertEquals("cinco mil quatrocentos e trinta e dois reais e dezenove centavos", cts.getRemuneracaoExtenso());
  }
    
}
