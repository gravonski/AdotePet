package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;

import java.util.List;

public interface CachorroService {
    List<Cachorro> buscarTodos();
    void salvar(Cachorro cachorro);
    Cachorro buscarPorId(Long id);
}
