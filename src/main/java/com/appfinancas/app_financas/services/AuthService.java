package com.appfinancas.app_financas.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.appfinancas.app_financas.domain.user.User;
import com.appfinancas.app_financas.dto.auth.login.LoginRequestDTO;
import com.appfinancas.app_financas.dto.auth.login.LoginResponseDTO;
import com.appfinancas.app_financas.dto.auth.register.RegisterRequestDTO;
import com.appfinancas.app_financas.dto.auth.register.RegisterResponseDTO;
import com.appfinancas.app_financas.infra.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginResponseDTO login(LoginRequestDTO userDTO) {
        User user = userService.findByEmail(userDTO.email());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(userDTO.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = tokenService.generateToken(user);
        return new LoginResponseDTO(user.getName(), token);
    }

    public RegisterResponseDTO register(RegisterRequestDTO userDTO) {
        User user = userService.findByEmail(userDTO.email());
        if (user != null) {
            throw new RuntimeException("User already exists");
        }
        user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setBalance(userDTO.balance());
        user.setTotalIncome(userDTO.totalIncome());
        userService.save(user);
        String token = tokenService.generateToken(user);
        return new RegisterResponseDTO(token, user.getName(), user.getEmail(), user.getBalance(),
                user.getTotalIncome());
    }

    public User getAuthenticatedUser() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }
        return (User) authentication.getPrincipal();
    }
}
