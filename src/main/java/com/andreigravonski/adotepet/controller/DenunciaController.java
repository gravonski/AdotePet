package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class DenunciaController {
    private DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }
}
