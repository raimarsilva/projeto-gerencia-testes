package com.projeto.teste.ProjetoTestes.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeHttpRequests(
            auth -> auth.antMatchers("/login", "/error", "/css/**").permitAll().anyRequest().authenticated())
        .formLogin(form -> form.defaultSuccessUrl("/home", true).permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/login"));

    return http.build();
  }

  @Bean
  public UserDetailsService users() {
    UserDetails user = User.withUsername("user").password("{noop}r4im4ni4").roles("ADMIN").build();

    return new InMemoryUserDetailsManager(user);
  }
}
