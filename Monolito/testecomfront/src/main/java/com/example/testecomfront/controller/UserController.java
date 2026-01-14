package com.example.testecomfront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/listar")
    public String findAll(Model model){
        List<User> users =  repository.findAll();
        model.addAttribute("usuariosNoHtml", users);
        return "usuarios";
    }
}