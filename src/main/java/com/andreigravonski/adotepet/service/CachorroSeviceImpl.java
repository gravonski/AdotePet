package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.repository.CachorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CachorroSeviceImpl implements CachorroService {

    @Autowired
    private CachorroRepository cachorroRepository;
}
