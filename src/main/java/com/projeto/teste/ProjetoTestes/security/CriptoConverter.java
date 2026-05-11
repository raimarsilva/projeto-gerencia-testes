package com.projeto.teste.ProjetoTestes.security;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CriptoConverter implements AttributeConverter<String, String> {

  private static final String TRANSFORMATION = "AES/GCM/NoPadding";
  private static final String ENV_KEY = "DB_CRIPTO_KEY";
  private static final int GCM_IV_LENGTH = 12;
  private static final int GCM_TAG_LENGTH_BITS = 128;
  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

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
      byte[] iv = new byte[GCM_IV_LENGTH];
      SECURE_RANDOM.nextBytes(iv);

      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(
          Cipher.ENCRYPT_MODE,
          getKey(),
          new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv)
      );

      byte[] encrypted = cipher.doFinal(attribute.getBytes(StandardCharsets.UTF_8));
      byte[] combined = new byte[iv.length + encrypted.length];
      System.arraycopy(iv, 0, combined, 0, iv.length);
      System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

      return Base64.getEncoder().encodeToString(combined);
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
      byte[] combined = Base64.getDecoder().decode(dbData);
      if (combined.length <= GCM_IV_LENGTH) {
        throw new IllegalArgumentException("Dado criptografado inválido");
      }

      byte[] iv = new byte[GCM_IV_LENGTH];
      byte[] encrypted = new byte[combined.length - GCM_IV_LENGTH];
      System.arraycopy(combined, 0, iv, 0, GCM_IV_LENGTH);
      System.arraycopy(combined, GCM_IV_LENGTH, encrypted, 0, encrypted.length);

      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(
          Cipher.DECRYPT_MODE,
          getKey(),
          new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv)
      );

      return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao descriptografar atributo", e);
    }
  }
}
