package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.service.CachorroService;
import com.andreigravonski.adotepet.service.CachorroSeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/caes")
public class CachorroController {
    private final CachorroService cachorroService;

    @Autowired
    public CachorroController(CachorroService cachorroService) {
        this.cachorroService = cachorroService;
    }

    @GetMapping("/listar")
    public String listarCaes(Model model) {
        model.addAttribute ("caes", cachorroService.buscarTodos());
        return "caes/listar";
    }

    @PostMapping("/salvar")
    public String salvarCao(@ModelAttribute Cachorro cachorro) {
        cachorroService.salvar(cachorro);
        return "redirect:/caes/listar";
    }
}

