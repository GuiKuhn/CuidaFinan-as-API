package com.appfinancas.app_financas.dto.home;

import java.util.List;

import com.appfinancas.app_financas.dto.transaction.HomePageTransactionDTO;

public record HomePageDTO(String name, double balance, double totalIncome, double totalExpenses, double totalSavings,
        List<HomePageTransactionDTO> transactions) {
}