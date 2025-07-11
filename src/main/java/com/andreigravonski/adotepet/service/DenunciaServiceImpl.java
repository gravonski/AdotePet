package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Override
    public List<Denuncia> buscarTodos(){
        return denunciaRepository.findAll();
    }

    @Override
    public Denuncia buscarPorId(Long id){
        return denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada!"));
    }

    @Override
    public void salvar(Denuncia denuncia) {
        denunciaRepository.save(denuncia);
    }

    @Override
    public void deletarPorId(Long id){
        denunciaRepository.deleteById(id);
    }

    @Autowired
    private DenunciaRepository denunciaRepository;
}
