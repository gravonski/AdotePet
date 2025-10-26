package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.model.ONG;

import java.util.List;

public interface DenunciaService {
    List<Denuncia> buscarTodos();
     void salvar(Denuncia denuncia);
     Denuncia buscarPorId(Long id);
     void deletarPorId(Long id);
    Denuncia salvarDenunciaPublica(Denuncia denuncia);
    List<Denuncia> buscarDenunciasDisponiveis();
    List<Denuncia> buscarDenunciasPorOng(ONG ong);
}