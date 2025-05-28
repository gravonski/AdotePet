package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.ONGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ONGServiceImpl implements ONGService {

    @Override
    public List<ONG> listarONG() {
        return ongRepository.findAll();
    }

    @Override
    public void salvarONG(ONG ong) {
        ongRepository.save(ong);
    }
    @Autowired
    private ONGRepository ongRepository;
}
