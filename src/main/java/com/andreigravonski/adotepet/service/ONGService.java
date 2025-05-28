package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.ONG;

import java.util.List;

public interface ONGService {
    List<ONG> listarONG();

    public void salvarONG(ONG ong);
}
