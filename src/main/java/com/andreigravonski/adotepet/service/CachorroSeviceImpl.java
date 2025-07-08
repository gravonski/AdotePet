package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CachorroSeviceImpl implements CachorroService{

    @Override
    public List<Cachorro> buscarTodos() {
        return cachorroRepository.findAll();
    }

    public Cachorro buscarPorId(Long id) {
        return cachorroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cão não encontrado"));
    }

    @Override
    public void salvar(Cachorro cachorro) {
        cachorroRepository.save(cachorro);
    }

    @Override
    public void deletarPorId(Long id) {
        cachorroRepository.deleteById(id);
    }

    @Autowired
    private CachorroRepository cachorroRepository;
}
