package com.fonseca.DesafioBackEnd.repositories;

import com.fonseca.DesafioBackEnd.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserByEmail(String email);
}
