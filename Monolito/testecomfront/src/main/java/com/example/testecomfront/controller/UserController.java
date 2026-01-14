package com.example.testecomfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.testecomfront.entities.User;
import com.example.testecomfront.repository.UserRepository;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserRepository repository;

    @PostMapping("/registered")
    public String register(User user){
        repository.save(user);
        return "redirect:/success.html";
    }

}