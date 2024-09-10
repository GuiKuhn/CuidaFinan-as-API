package com.appfinancas.app_financas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appfinancas.app_financas.domain.expense.Transaction;
import com.appfinancas.app_financas.dto.transaction.PostTransactionRequestDTO;
import com.appfinancas.app_financas.services.ExpenseService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/")
    public ResponseEntity<Transaction> registerTransaction(@RequestBody PostTransactionRequestDTO expense){
        return ResponseEntity.ok(expenseService.registerTransaction(expense));
    }

    @GetMapping("/")
    public ResponseEntity<?> listTransactions(){
        return ResponseEntity.ok(expenseService.findAll());
    }
}
