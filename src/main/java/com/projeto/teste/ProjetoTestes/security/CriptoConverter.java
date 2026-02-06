package com.projeto.teste.ProjetoTestes.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CriptoConverter implements AttributeConverter<String, String>{

  private static final String ALGORITHM = "AES";

  // deve ser o mesmo env no ambiente onde o app roda.
  private static final String SECRET = System.getenv("DB_CRIPTO_KEY");

  private SecretKeySpec getKey() {
        return new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
    }

  @Override
  public String convertToDatabaseColumn(String attribute) {
      if(attribute == null) return null;

      try{
          Cipher cipher = Cipher.getInstance(ALGORITHM);
          cipher.init(Cipher.ENCRYPT_MODE, getKey());
          return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
      } catch (Exception e) {
          throw new RuntimeException("Erro ao criptografar o atributo", e);
      }
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
      if (dbData == null) return null;

      try {
          Cipher cipher = Cipher.getInstance(ALGORITHM);
          cipher.init(Cipher.DECRYPT_MODE, getKey());
          return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
      } catch (Exception e) {
          throw new RuntimeException("Erro ao decifrar", e);
      }
  }
    
}
