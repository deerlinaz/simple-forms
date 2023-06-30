package com.example.simpleforms.service;

import com.example.simpleforms.model.UsersModels;
import com.example.simpleforms.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    public UsersModels registerUser(String login, String password, String email ){
        if(login ==null || password ==null){
            return null;
        }
        else{
            UsersModels usersModels = new UsersModels();
            usersModels.setLogin(login);
            usersModels.setPassword(password);
            usersModels.setEmail(email);
            return usersRepository.save(usersModels);

        }


    }

    public UsersModels authenticate(String login, String password){
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
