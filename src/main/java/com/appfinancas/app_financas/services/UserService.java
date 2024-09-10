package com.appfinancas.app_financas.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appfinancas.app_financas.domain.user.User;
import com.appfinancas.app_financas.dto.home.HomePageDTO;
import com.appfinancas.app_financas.repositories.IUserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ExpenseService expenseService;

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public HomePageDTO getHomePage() {

        User user = authService.getAuthenticatedUser();
        return new HomePageDTO(user.getName(), user.getBalance(), user.getTotalIncome(), user.getTotalExpense(),
                user.getTotalIncome() - user.getTotalExpense(), expenseService.getHomePageTransactions());

    }

}
