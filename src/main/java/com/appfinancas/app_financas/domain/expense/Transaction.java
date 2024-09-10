package com.appfinancas.app_financas.domain.expense;

import java.time.LocalDateTime;

import com.appfinancas.app_financas.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String involved;
    private String description;
    private double value;
    private String type;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}
