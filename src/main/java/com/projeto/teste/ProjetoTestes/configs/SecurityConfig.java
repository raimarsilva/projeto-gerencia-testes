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
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
                http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(
                                "/", 
                                "/home", 
                                "/login", 
                                "/error", 
                                "/index", 
                                "/css/**",
                                "/swagger-ui.html").permitAll()
                                .anyRequest().authenticated()
                        )
                        .formLogin().and().logout();

                return http.build();
        }

        @Bean
        public UserDetailsService users(){
                UserDetails user = User
                .withUsername("user")
                .password("{noop}r4im4ni4")
                .roles("ADMIN")
                .build();

                return new InMemoryUserDetailsManager(user);
        }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .antMatchers("/", "/home", "/login", "/error", "/index", "/css/**", "/swagger-ui.html").permitAll()
                        .antMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login-error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
    }
*/


/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password("{noop}password")
            .roles("USER");
    }
    */
}
