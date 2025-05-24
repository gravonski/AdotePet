package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.repository.ONGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ONGServiceImpl implements ONGService {

    @Autowired
    private ONGRepository ongRepository;
}
