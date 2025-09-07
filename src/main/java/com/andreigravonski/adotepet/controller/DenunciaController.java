package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.model.StatusDenuncia;
import com.andreigravonski.adotepet.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/denuncias")

public class DenunciaController {
    private DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @GetMapping("/listar")
    public String listarDenuncias(Model model) {
        model.addAttribute("denuncias", denunciaService.buscarTodos());
        return "denuncias/listar";
    }

    @GetMapping("/novo")
    public String novoDenuncia(Model model) {
        model.addAttribute("denuncia", new Denuncia());
        model.addAttribute("statusOptions", StatusDenuncia.values());
        return "denuncias/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarDenuncia(@PathVariable Long id, Model model) {
        Denuncia denuncia = denunciaService.buscarPorId(id);
        model.addAttribute("denuncia", denuncia);
        model.addAttribute("statusOptions", StatusDenuncia.values());
        return "denuncias/formulario";
    }

    @PostMapping("/salvar")
    public String salvarDenuncia(@ModelAttribute Denuncia denuncia, RedirectAttributes redirectAttributes) {
        denunciaService.salvar(denuncia);
        redirectAttributes.addFlashAttribute("mensagem", "Denúncia salva com sucesso!");
        return "redirect:/denuncias/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarDenuncia(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        denunciaService.deletarPorId(id);
        redirectAttributes.addFlashAttribute("mensagem", "Denúncia deletada com sucesso!");
        return "redirect:/denuncias/listar";
    }

    @GetMapping("/denunciar")
    public String mostrarFormularioDenunciaPublico(Model model) {
        model.addAttribute("denuncia", new Denuncia());
        // Não precisamos mais dos StatusOptions aqui se a denúncia sempre começa como PENDENTE
        return "denuncias/formulario-publico";
    }


    @GetMapping("/denuncia-sucesso")
    public String mostrarPaginaDeSucesso() {
        return "denuncias/denuncia-sucesso"; // O caminho para o seu arquivo HTML
    }

    @PostMapping("/denunciar/salvar")
    public String salvarDenunciaPublica(@ModelAttribute Denuncia denuncia, RedirectAttributes redirectAttributes) {
        // Regra de negócio: Toda denúncia pública começa como PENDENTE
        denuncia.setStatus(StatusDenuncia.PENDENTE);
        denunciaService.salvar(denuncia);

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Sua denúncia foi registrada com sucesso! Agradecemos sua colaboração.");
        return "redirect:/denuncia-sucesso";
    }


}