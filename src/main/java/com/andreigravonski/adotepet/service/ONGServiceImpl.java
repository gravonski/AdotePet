package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.ONGRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ONGServiceImpl implements ONGService {

    @Override
    public List<ONG> buscarONG() {
        return ongRepository.findAll();
    }

    @Override
    public ONG buscarPorId(Long id) {
        return ongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ONG nao encontrada!"));
    }

    @Override
    public void salvarONG(ONG ong) {
        ongRepository.save(ong);
    }

    @Override
    public void deletarPorId(Long id) {
        ongRepository.deleteById(id);
    }

    @Autowired
    private ONGRepository ongRepository;
}
