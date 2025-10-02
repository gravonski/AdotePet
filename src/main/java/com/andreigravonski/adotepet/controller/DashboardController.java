package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.ONG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import com.andreigravonski.adotepet.repository.DenunciaRepository;
import com.andreigravonski.adotepet.model.StatusDenuncia;


@Controller
public class DashboardController {

    private final DenunciaRepository denunciaRepository;
    private final CachorroRepository cachorroRepository;

    @Autowired
    public DashboardController(DenunciaRepository denunciaRepository, CachorroRepository cachorroRepository) {
        this.denunciaRepository = denunciaRepository;
        this.cachorroRepository = cachorroRepository;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(Authentication authentication, Model model) {
        ONG ongLogada = (ONG) authentication.getPrincipal();
        model.addAttribute("nomeOng", ongLogada.getNome());
        long totalCaesDaOng = cachorroRepository.countByOng(ongLogada);
        long denunciasPendentes = denunciaRepository.countByStatus(StatusDenuncia.PENDENTE);
        model.addAttribute("totalCaesDaOng", totalCaesDaOng);
        model.addAttribute("denunciasPendentes", denunciasPendentes);
        return "dashboard";
    }
}
