package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.repository.CachorroRepository;
import com.andreigravonski.adotepet.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
}
