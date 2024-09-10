package com.appfinancas.app_financas.dto.transaction;

public record PostTransactionRequestDTO(String involved, String description, double value, String type) {

}
