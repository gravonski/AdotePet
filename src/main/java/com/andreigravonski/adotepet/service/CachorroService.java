package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;

import java.util.List;

public interface CachorroService {
    List<Cachorro> buscarTodos();
    public void salvar(Cachorro cachorro);
}
