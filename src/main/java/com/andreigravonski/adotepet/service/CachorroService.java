package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.ONG;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CachorroService {
    List<Cachorro> buscarTodos();
    Cachorro buscarPorId(Long id);
    void salvar(Cachorro cachorro, MultipartFile imagem);
    void deletarPorId(Long id);
    List<Cachorro> buscarPorOng(ONG ong);
    List<Cachorro> buscarCaesFiltrado(ONG ong, String raca, Integer idade);
}