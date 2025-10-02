package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.ONG;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String mostrarDashboard(Authentication authentication, Model model) {
        ONG ongLogada = (ONG) authentication.getPrincipal();
        model.addAttribute("nomeOng", ongLogada.getNome());
        return "dashboard";
    }
}
