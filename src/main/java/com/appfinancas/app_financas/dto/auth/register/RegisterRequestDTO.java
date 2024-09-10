package com.appfinancas.app_financas.dto.auth.register;

public record RegisterRequestDTO(String name, String email, String password, double balance, double totalIncome) {
    
}
