package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.service.ONGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ONGController {
    private ONGService ongService;

    @Autowired
    public ONGController(ONGService ongService) {
        this.ongService = ongService;
    }
}
