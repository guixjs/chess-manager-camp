package com.projetos.back_chess_manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private SecurityFilter securityFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
          // rotas publicas
          auth.requestMatchers("/user/new").permitAll()
              .requestMatchers("/user/auth").permitAll()

              // h2
              .requestMatchers("/h2-console/**").permitAll()
              .requestMatchers(PathRequest.toH2Console()).permitAll()

              // rotas para ORGANIZADOR
              .requestMatchers("/camp/new").hasRole("ORGANIZADOR")

              // rotas para JOGADOR
              .requestMatchers("/camp/apply").hasRole("JOGADOR")

        ;

          // rotas privadas
          auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
        .headers(headers -> headers
            .frameOptions(frame -> frame.sameOrigin()));
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
