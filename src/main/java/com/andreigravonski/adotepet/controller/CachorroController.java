package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.service.CachorroService;
import com.andreigravonski.adotepet.service.CachorroSeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/editar/{id}")
    public String editarCaes(@PathVariable Long id, Model model) {
        Cachorro cachorro = cachorroService.buscarPorId(id);
        model.addAttribute("cao", cachorro);
        return "formularioCao";
    }

    @PostMapping("/salvar")
    public String salvarCao(@ModelAttribute Cachorro cachorro, RedirectAttributes redirectAttributes) {
        cachorroService.salvar(cachorro);
        redirectAttributes.addFlashAttribute("mensagem", "Cão salvo com sucesso!");
        return "redirect:/caes/listar";
    }
}

