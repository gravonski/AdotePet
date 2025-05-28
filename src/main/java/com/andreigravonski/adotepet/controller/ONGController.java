package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.service.ONGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ong")
public class ONGController {
    private ONGService ongService;

    @Autowired
    public ONGController(ONGService ongService) {
        this.ongService = ongService;
    }

    @GetMapping("/listar")
    public String listarONG(Model model) {
        model.addAttribute("ongs", ongService.listarONG());
        return "ong/listar";
    }

    @PostMapping("/salvar")
    public String salvarONG(ONG ong) {
        ongService.salvarONG(ong);
        return "redirect:/ong/listar";
    }
}
