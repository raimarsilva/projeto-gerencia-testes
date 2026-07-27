package com.projeto.teste.projetotestes.utils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatterPtBr {
  private DateFormatterPtBr() {
    /* This utility class should not be instantiated */
  }

  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy",
      new Locale("pt", "BR"));

}
