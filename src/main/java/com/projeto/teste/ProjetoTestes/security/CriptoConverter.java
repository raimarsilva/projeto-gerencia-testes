package com.projeto.teste.ProjetoTestes.security;

import java.nio.ByteBuffer;
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

  private static final int IV_BYTE_LENGTH = 12;
  private static final int TAG_BIT_LENGTH = 128;

  private final SecureRandom secureRandom = new SecureRandom();

  private SecretKeySpec getKey() {
    String secret = System.getenv(ENV_KEY);

    if (secret == null || secret.isBlank()) {
      throw new IllegalStateException("Chave pública não encontrada.");
    }

    byte[] key = secret.getBytes();

    if (key.length != 16 && key.length != 24 && key.length != 32) {
      throw new IllegalStateException("Chave pública deve ter 16, 24 ou 32 bytes");
    }

    return new SecretKeySpec(key, "AES");
  }

  @Override
  public String convertToDatabaseColumn(String attribute) {
    if (attribute == null || attribute.isBlank()) {
      return null;
    }

    try {
      byte[] iv = new byte[IV_BYTE_LENGTH];
      secureRandom.nextBytes(iv);

      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
      cipher.init(Cipher.ENCRYPT_MODE, getKey(), gcmParameterSpec);

      byte[] encryptedData = cipher.doFinal(attribute.getBytes());

      byte[] encryptedBuffer = ByteBuffer.allocate(iv.length + encryptedData.length).put(iv).put(encryptedData).array();

      return Base64.getEncoder().encodeToString(encryptedBuffer);
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
      byte[] encryptedBuffer = Base64.getDecoder().decode(dbData);

      ByteBuffer buffer = ByteBuffer.wrap(encryptedBuffer);
      byte[] iv = new byte[IV_BYTE_LENGTH];
      buffer.get(iv);

      byte[] encryptedData = new byte[buffer.remaining()];
      buffer.get(encryptedData);

      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
      cipher.init(Cipher.DECRYPT_MODE, getKey(), gcmParameterSpec);

      return new String(cipher.doFinal(encryptedData));
    } catch (Exception e) {
      throw new RuntimeException("Erro ao descriptografar atributo", e);
    }
  }
}
