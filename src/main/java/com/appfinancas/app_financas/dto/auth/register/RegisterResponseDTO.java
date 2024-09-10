package com.appfinancas.app_financas.dto.auth.register;

public record RegisterResponseDTO(String token, String name, String email, double balance, double totalIncome) {
    
}
