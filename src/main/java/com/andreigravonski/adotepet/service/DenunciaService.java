package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Denuncia;

import java.util.List;

public interface DenunciaService {
    List<Denuncia> buscarTodos();
     void salvar(Denuncia denuncia);
     Denuncia buscarPorId(Long id);
     void deletarPorId(Long id);

}