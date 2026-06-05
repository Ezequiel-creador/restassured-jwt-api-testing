package com.proyect.testing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApiSecurityConfig {

@Autowired
private JwtFilter jwtFilter;

   @Bean
   @Order(1)
           public SecurityFilterChain apifilterChain(HttpSecurity http) throws Exception {
           
            http
            .securityMatcher("/api/**")
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login").permitAll()
            .anyRequest()
            .authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
            return http.build();
         }
}
