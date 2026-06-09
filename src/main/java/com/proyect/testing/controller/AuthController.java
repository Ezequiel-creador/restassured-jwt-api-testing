package com.proyect.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.testing.dto.LoginRequest;
import com.proyect.testing.dto.LoginResponse;
import com.proyect.testing.security.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
@Autowired 
private AuthenticationManager authenticationManager;

@Autowired
private JwtService jwtService;

@Operation(summary = "Genera un JWT válido")
@PostMapping("/login")
public LoginResponse login(@RequestBody LoginRequest request){
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));
    String token = jwtService.generateToken(request.getUsername());
    return new LoginResponse(token);
}


}
