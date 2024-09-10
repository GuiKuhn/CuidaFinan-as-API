package com.appfinancas.app_financas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.appfinancas.app_financas.domain.expense.Transaction;
import com.appfinancas.app_financas.domain.user.User;
import com.appfinancas.app_financas.dto.transaction.HomePageTransactionDTO;
import com.appfinancas.app_financas.dto.transaction.PostTransactionRequestDTO;
import com.appfinancas.app_financas.repositories.ITransactionRepository;


@Service
public class ExpenseService {
    
    @Autowired
    private ITransactionRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;
    
    public Transaction registerTransaction(PostTransactionRequestDTO expense) {
        
        User user = authService.getAuthenticatedUser();
        
        Transaction newExpense = new Transaction();
        newExpense.setInvolved(expense.involved());
        newExpense.setDescription(expense.description());
        newExpense.setValue(expense.value());
        newExpense.setType(expense.type());
        newExpense.setUser(user);

        if(expense.type().equals("EXPENSE")) {
            user.setBalance(user.getBalance() - expense.value());
            user.setTotalExpense(user.getTotalExpense() + expense.value());
        } else {
            user.setBalance(user.getBalance() + expense.value());
        }
        userService.save(user);
        
        return expenseRepository.save(newExpense);
    }


    public List<Transaction> findAll() {
        User user = authService.getAuthenticatedUser();
        return expenseRepository.findByUser(user);
    }

    public List<HomePageTransactionDTO> getHomePageTransactions() {
        User user = authService.getAuthenticatedUser();
        return expenseRepository.findByUser(user).stream().map(transaction -> new HomePageTransactionDTO(transaction.getInvolved(),transaction.getDescription(), transaction.getValue(), transaction.getType())).toList();
    }

    

}
