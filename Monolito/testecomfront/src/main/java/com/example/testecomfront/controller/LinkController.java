package com.example.testecomfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.testecomfront.entities.Link;
import com.example.testecomfront.entities.User;
import com.example.testecomfront.repository.LinkRepository;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkRepository repository;

    @PostMapping("/adicionar")
    public String add(@RequestParam String name, @RequestParam String url, HttpSession session) {
        User logado = (User) session.getAttribute("usuarioLogado");
        if (logado == null)
            return "redirect:/user/login";
        else {
            Link link = new Link();
            link.setName(name); 
            link.setUrl(url);
            link.setUser(logado);

            repository.save(link);
        }
        return "redirect:/user/dashboard";
    }

    @GetMapping("/excluir/{id}")
    public String deleteById(@PathVariable("id") Long id){
        repository.deleteById(id);

        return "redirect:/user/dashboard";
    }
}
