package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.ONG;

import java.util.List;

public interface CachorroService {
    List<Cachorro> buscarTodos();
    void salvar(Cachorro cachorro);
    Cachorro buscarPorId(Long id);
    void deletarPorId(Long id);
    List<Cachorro> buscarPorOng(ONG ong);
}
