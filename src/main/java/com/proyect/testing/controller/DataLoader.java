package com.proyect.testing.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyect.testing.model.User;
import com.proyect.testing.repository.UserRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("1234"));
            userRepository.save(user);
            System.out.println("Usuario admin creado con exito");  
        };
    }

}
