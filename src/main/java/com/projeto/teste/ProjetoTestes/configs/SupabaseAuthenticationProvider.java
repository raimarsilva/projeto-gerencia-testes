package com.projeto.teste.ProjetoTestes.configs;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SupabaseAuthenticationProvider implements AuthenticationProvider {

  // Injeta a chave do application.properties
  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuerUri;

  @Value("${supabase.anon.key}")
  private String anonKey;

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {

    String email = auth.getName();
    String password = auth.getCredentials().toString();

    String loginUrl = issuerUri + "/token?grant_type=password";

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("apikey", anonKey);

    Map<String, String> body = Map.of("email", email, "password", password);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

    try {
      restTemplate.postForEntity(loginUrl, request, Map.class);

      return new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());

    } catch (Exception e) {

      System.err.println("ERRO SUPABASE: " + e.getMessage());

      throw new BadCredentialsException("Usuário ou senha inválidos no Supabase.");
    }
  }

  @Override
  public boolean supports(Class<?> auth) {
    return auth.equals(UsernamePasswordAuthenticationToken.class);
  }

}
