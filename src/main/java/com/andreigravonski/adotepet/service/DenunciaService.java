package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.repository.DenunciaRepository;

import java.util.List;

public interface DenunciaService {
    List<Denuncia> listarDenuncias();

    public void salvarDenuncia(Denuncia denuncia);

}