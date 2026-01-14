package com.example.testecomfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testecomfront.entities.User;
import com.example.testecomfront.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserRepository repository;

    @PostMapping("/registered")
    public String register(User user){
        repository.save(user);
        return "User " + user.getName() + " has successfully registered!";
    }

}