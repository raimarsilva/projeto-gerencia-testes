package com.projeto.teste.projetotestes.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final SupabaseAuthenticationProvider authProvider;

  private static final String LOGINURL = "/login";

  public SecurityConfig(SupabaseAuthenticationProvider authProvider) {
    this.authProvider = authProvider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(Customizer.withDefaults()).authenticationProvider(authProvider)
        .authorizeHttpRequests(
            auth -> auth.antMatchers(LOGINURL, "/error", "/css/**").permitAll().anyRequest().authenticated())
        .formLogin(form -> form.loginPage(LOGINURL).defaultSuccessUrl("/home", true).permitAll())
        .logout(logout -> logout.logoutSuccessUrl(LOGINURL).deleteCookies("JSESSIONID").invalidateHttpSession(true));

    return http.build();
  }
}