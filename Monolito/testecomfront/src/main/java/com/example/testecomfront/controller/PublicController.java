package com.example.testecomfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.testecomfront.entities.User;
import com.example.testecomfront.repository.LinkRepository;
import com.example.testecomfront.repository.UserRepository;

@Controller
public class PublicController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LinkRepository linkRepository;

    @GetMapping("/u/{name}")
    public String verPerfil(@PathVariable String name, Model model) {
        User usuario = userRepository.findByName(name);
        if (usuario == null) return "redirect:/";
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("links", linkRepository.findByUser(usuario));
        return "perfil";
    }
}