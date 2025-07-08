package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.service.ONGService;
import org.aspectj.lang.annotation.DeclareWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ongs")
public class ONGController {
    private ONGService ongService;

    @Autowired
    public ONGController(ONGService ongService) {
        this.ongService = ongService;
    }

    @GetMapping("/novo")
    public String mostrarNovo(Model model) {
        model.addAttribute("ong", new ONG());
        return "ongs/formulario";
    }

    @GetMapping("/listar")
    public String listarONG(Model model) {
        model.addAttribute("ongs", ongService.buscarONG());
        return "ongs/listar";
    }

    @GetMapping("/editar/{id}")
    public String listarONG(@PathVariable Long id, Model model) {
        ONG ong = ongService.buscarPorId(id);
        model.addAttribute("ong", ong);
        return "ongs/formulario";
    }

    @PostMapping("/salvar")
    public String salvarONG(ONG ong, RedirectAttributes redirectAttributes) {
        ongService.salvarONG(ong);
        redirectAttributes.addFlashAttribute("mensagem", "ONG salva com sucesso!");
        return "redirect:/ongs/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarONG(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ongService.deletarPorId(id);
        redirectAttributes.addFlashAttribute("mensagem", "ONG deletada com sucesso!");
        return "redirect:/ongs/listar";
    }
}
