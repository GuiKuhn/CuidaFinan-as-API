package com.appfinancas.app_financas.repositories;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appfinancas.app_financas.domain.user.User;

public interface IUserRepository extends JpaRepository<User, UUID> {
    
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    User findByEmailAndPassword(String email, String password);
    
}
