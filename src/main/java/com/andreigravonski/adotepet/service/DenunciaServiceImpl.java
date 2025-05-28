package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import com.andreigravonski.adotepet.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Override
    public List<Denuncia> listarDenuncias() {
        return denunciaRepository.findAll();
    }

    @Override
    public void salvarDenuncia(Denuncia denuncia) {
        denunciaRepository.save(denuncia);
    }

    @Autowired
    private DenunciaRepository denunciaRepository;
}
