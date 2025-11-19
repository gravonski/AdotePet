package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.model.StatusDenuncia;
import com.andreigravonski.adotepet.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/denuncias")

public class DenunciaController {
    private DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @GetMapping("/listar")
    public String listarDenuncias(Model model, Authentication authentication) {
        ONG ongLogada = (ONG) authentication.getPrincipal();
        List <Denuncia> denuciasDisponiveis = denunciaService.buscarDenunciasDisponiveis();
        List <Denuncia> minhasDenuncias = denunciaService.buscarDenunciasPorOng(ongLogada);
        model.addAttribute("denunciasDisponiveis", denuciasDisponiveis);
        model.addAttribute("minhasDenuncias", minhasDenuncias);
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
        return "denuncias/formulario-publico";
    }

 
    @GetMapping("/denuncia-sucesso")
    public String mostrarPaginaDeSucesso() {
        return "denuncias/denuncia-sucesso";
    }

    @PostMapping("/denunciar/salvar")
    public String salvarDenunciaPublica(@Valid @ModelAttribute Denuncia denuncia,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        Model model) {
        if (result.hasErrors()) {
            return "denuncias/formulario-publico";
        }
        denuncia.setStatus(StatusDenuncia.PENDENTE);
        denunciaService.salvarDenunciaPublica(denuncia);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Sua denúncia foi registrada com sucesso! Agradecemos sua colaboração.");
        return "redirect:/denuncias/denuncia-sucesso";
    }

    @GetMapping("/resgatar/{id}")
    public String resgatarDenuncia(@PathVariable Long id) {
        Denuncia denuncia = denunciaService.buscarPorId(id);
        denuncia.setStatus(StatusDenuncia.RESGATADO);
        denunciaService.salvar(denuncia);
        return "redirect:/denuncias/listar";
    }

    @GetMapping("/ignorar/{id}")
    public String ignorarDenuncia (@PathVariable Long id) {
        Denuncia denuncia = denunciaService.buscarPorId(id);
        denuncia.setStatus(StatusDenuncia.IGNORADO);
        denunciaService.salvar(denuncia);
        return "redirect:/denuncias/listar";
    }
}