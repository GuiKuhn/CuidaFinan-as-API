package com.appfinancas.app_financas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appfinancas.app_financas.domain.expense.Transaction;
import com.appfinancas.app_financas.domain.user.User;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByUser(User user);
    
}
