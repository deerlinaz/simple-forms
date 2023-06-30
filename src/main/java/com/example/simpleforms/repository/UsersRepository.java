package com.example.simpleforms.repository;

import com.example.simpleforms.model.UsersModels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModels, Integer> {

    Optional<UsersModels> findByLoginAndPassword(String login, String password);


}
