package com.appfinancas.app_financas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appfinancas.app_financas.dto.auth.login.LoginRequestDTO;
import com.appfinancas.app_financas.dto.auth.login.LoginResponseDTO;
import com.appfinancas.app_financas.dto.auth.register.RegisterRequestDTO;
import com.appfinancas.app_financas.dto.auth.register.RegisterResponseDTO;
import com.appfinancas.app_financas.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO userDTO) {
        return ResponseEntity.ok(authService.login(userDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO userDTO) {
        return ResponseEntity.ok(authService.register(userDTO));
    }
    
}
