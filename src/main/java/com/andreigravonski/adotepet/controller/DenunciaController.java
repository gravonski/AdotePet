package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/denuncia")

public class DenunciaController {
    private DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @GetMapping("/listar")
    public String listarDenuncias(Model model) {
        model.addAttribute("denuncias", denunciaService.listarDenuncias());
        return "denuncia/listar";
    }

    @PostMapping("/salvar")
    public String salvarDenuncia(@ModelAttribute Denuncia denuncia, RedirectAttributes redirectAttributes) {
        denunciaService.salvarDenuncia(denuncia);
        redirectAttributes.addFlashAttribute("mensagem", "Denúncia salva com sucesso!");
        return "redirect:/denuncia/listar";
    }

}
