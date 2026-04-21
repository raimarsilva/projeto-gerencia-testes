package com.projeto.teste.ProjetoTestes.configs;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private SupabaseAuthenticationProvider authProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            auth -> auth.antMatchers("/login", "/error", "/css/**").permitAll().anyRequest().authenticated())
        .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/login").deleteCookies("JSESSIONID"));
    // .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
    // jwt.decoder(jwtDecoder())));

    return http.build();
  }
  /*
   * @Bean public JwtDecoder jwtDecoder() { return
   * JwtDecoders.fromIssuerLocation(issuerUri); }
   */

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authProvider);
  }
}