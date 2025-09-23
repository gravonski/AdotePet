package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.ONG;

import java.util.List;

public interface ONGService {
    List<ONG> buscarONG();
    void salvarONG(ONG ong);
    ONG buscarPorId(Long id);
    void deletarPorId(Long id);
    void registrarNovaOng(ONG ong);
}
