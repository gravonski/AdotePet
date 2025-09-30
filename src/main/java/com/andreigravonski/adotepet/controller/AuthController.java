package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.service.ONGService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private ONGService ongService;

    public AuthController(ONGService ongService) {
        this.ongService = ongService;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("ong", new ONG());
        return "registro"; // Nome do nosso arquivo HTML
    }

    // Futuramente, adicionaremos o método POST para salvar o registro aqui.
    // Dentro do AuthController.java
    @PostMapping("/registro")
    public String processarRegistro(@ModelAttribute ONG ong) {
        ongService.registrarNovaOng(ong);

        // Após o registro, redireciona para a página de login para que a ONG possa entrar
        return "redirect:/login?registro=sucesso";
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }
}