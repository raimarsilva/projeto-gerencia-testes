package com.projeto.teste.projetotestes.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;

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
        .headers(headers -> headers.cacheControl(Customizer.withDefaults()))
        .sessionManagement(session -> session.invalidSessionUrl(LOGINURL + "?timeout=true"))
        .authorizeHttpRequests(auth -> auth.requestMatchers(LOGINURL, "/error", "/css/**", "/*.jsf").permitAll()
            .anyRequest().authenticated())
        .formLogin(form -> form.loginPage(LOGINURL).defaultSuccessUrl("/home", true).permitAll())
        .logout(logout -> logout.logoutSuccessUrl(LOGINURL).invalidateHttpSession(true).clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .addLogoutHandler(new HeaderWriterLogoutHandler(
                new ClearSiteDataHeaderWriter(Directive.COOKIES, Directive.CACHE, Directive.STORAGE))))
        .exceptionHandling(exception -> exception.accessDeniedHandler((request, response, accessDeniedException) -> {
          if (accessDeniedException instanceof MissingCsrfTokenException
              || accessDeniedException instanceof InvalidCsrfTokenException) {
            response.sendRedirect("/login?expired=true");
          } else {
            response.sendRedirect("/error");
          }
        }));

    return http.build();
  }
}