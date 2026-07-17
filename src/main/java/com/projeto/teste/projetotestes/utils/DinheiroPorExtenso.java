package com.projeto.teste.projetotestes.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.extern.slf4j.Slf4j;
import pl.allegro.finance.tradukisto.MoneyConverters;

@Slf4j
public class DinheiroPorExtenso {
  private DinheiroPorExtenso() {
    /* This utility class should not be instantiated */
  }

  public static String paraReal(BigDecimal valor) {

    var converter = MoneyConverters.BRAZILIAN_PORTUGUESE_BANKING_MONEY_VALUE;
    String extenso = converter.asWords(valor);
    log.info("valor por extenso: " + extenso);
    return extenso;
  }

  public static String paraRealHumano(BigDecimal valor) {
    var inteiro = valor.setScale(0, RoundingMode.DOWN);
    var centavos = valor.subtract(inteiro).movePointRight(2).intValueExact();

    var valorExtenso = MoneyConverters.BRAZILIAN_PORTUGUESE_BANKING_MONEY_VALUE.asWords(inteiro).replace(" R$ 00/100",
        " reais");

    if (centavos > 0) {
      var centavosExtenso = MoneyConverters.BRAZILIAN_PORTUGUESE_BANKING_MONEY_VALUE
          .asWords(BigDecimal.valueOf(centavos)).replace(" R$ 00/100", "");

      return valorExtenso + " e " + centavosExtenso + " centavos";
    }

    return valorExtenso;
  }

}
