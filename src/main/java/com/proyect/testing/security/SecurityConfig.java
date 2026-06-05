package com.proyect.testing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
   }  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
   }
   
    @Bean
    @Order(2)
           public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
           http
            .securityMatcher("/login", "/users/**", "/css/**", "/h2-console/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**","/users/create", "/h2-console/**","/users/save").permitAll()
                .anyRequest().authenticated()
            ).csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
             .headers(headers -> headers
              .frameOptions(frame -> frame.disable())
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .defaultSuccessUrl("/users", true) 
                .permitAll()
            )   
            
           
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
            );

        return http.build();
    }

}