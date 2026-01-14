package com.example.testecomfront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.testecomfront.entities.Link;
import com.example.testecomfront.entities.User;
import com.example.testecomfront.repository.LinkRepository;
import com.example.testecomfront.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private LinkRepository linkRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registrar")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/registered")
    public String register(User user) {
        repository.save(user);
        return "redirect:/success.html";
    }

    @GetMapping("/listar")
    public String findAll(Model model) {
        List<User> users = repository.findAll();
        model.addAttribute("usuariosNoHtml", users);
        return "usuarios";
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/deletar/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/user/listar";
    }

    @GetMapping("/editar/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        User user = findById(id);

        model.addAttribute("usuarioParaEditar", user);

        return "editar";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = repository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("usuarioLogado", user);
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/user/login?error=true";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User logado = (User) session.getAttribute("usuarioLogado");
        if (logado == null)
            return "redirect:/user/login";

        List<Link> linksDoUsuario = linkRepository.findByUserId(logado.getId());

        model.addAttribute("usuario", logado);
        model.addAttribute("links", linksDoUsuario);
        return "dashboard";
    }
}