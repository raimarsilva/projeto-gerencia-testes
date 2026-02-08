package com.projeto.teste.ProjetoTestes.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CriptoConverter implements AttributeConverter<String, String> {

  private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
  private static final String ENV_KEY = "DB_CRIPTO_KEY";

  private SecretKeySpec getKey() {
    String secret = System.getenv(ENV_KEY);

    if (secret == null || secret.isBlank()) {
      throw new IllegalStateException(
          "Variável de ambiente DB_CRIPTO_KEY não definida"
      );
    }

    byte[] key = secret.getBytes();

    if (key.length != 16 && key.length != 24 && key.length != 32) {
      throw new IllegalStateException(
          "DB_CRIPTO_KEY deve ter 16, 24 ou 32 bytes (AES)"
      );
    }

    return new SecretKeySpec(key, "AES");
  }

  @Override
  public String convertToDatabaseColumn(String attribute) {
    if (attribute == null || attribute.isBlank()) {
      return null;
    }

    try {
      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(Cipher.ENCRYPT_MODE, getKey());
      return Base64.getEncoder()
                   .encodeToString(cipher.doFinal(attribute.getBytes()));
    } catch (Exception e) {
      throw new RuntimeException("Erro ao criptografar atributo", e);
    }
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isBlank()) {
      return null;
    }

    try {
      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(Cipher.DECRYPT_MODE, getKey());
      return new String(
          cipher.doFinal(Base64.getDecoder().decode(dbData))
      );
    } catch (Exception e) {
      throw new RuntimeException("Erro ao descriptografar atributo", e);
    }
  }
}
