package com.projeto.teste;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.projeto.teste.ProjetoTestes.security.CriptoConverter;

@SpringBootTest(classes = CriptoConverter.class, properties = "DB_CRIPTO_KEY=1234567890123456")
class CriptoConverterTest {

  CriptoConverter cc = new CriptoConverter();

  @Test
  void shouldCreateEncryptedValue() {
    String s = "atributo a ser cifrado";

    assertNotNull(cc.convertToDatabaseColumn(s));
  }
}
