package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.andreigravonski.adotepet.specification.CachorroSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class CachorroServiceImpl implements CachorroService {

    private final CachorroRepository cachorroRepository;
    private final FileStorageService fileStorageService;

    public CachorroServiceImpl(CachorroRepository cachorroRepository,
                               FileStorageService fileStorageService) { // <-- MODIFICADO
        this.cachorroRepository = cachorroRepository;
        this.fileStorageService = fileStorageService; // <-- MODIFICADO
    }

    @Override
    public List<Cachorro> buscarTodos() {
        return cachorroRepository.findAll();
    }

    @Override
    @PreAuthorize("@ongSecurityService.podeGerenciarCao(authentication, #id)")
    public Cachorro buscarPorId(Long id) {
        return cachorroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cão de ID " + id + " não encontrado!"));
    }

    @Override
    public List<Cachorro> buscarCaesFiltrado(ONG ong, String raca, Integer idade) { // <-- NOVO PARÂMETRO
        Specification<Cachorro> spec = Specification.where(CachorroSpecification.porOng(ong));

        if (raca != null && !raca.isBlank()) {
            spec = spec.and(CachorroSpecification.porRaca(raca));
        }
        if (idade != null) {
            spec = spec.and(CachorroSpecification.porIdadeMaxima(idade));
        }
        return cachorroRepository.findAll(spec);
    }

    @Override
    @PreAuthorize("#cachorro.id == null or @ongSecurityService.podeGerenciarCao(authentication, #cachorro.id)")
    public void salvar(Cachorro cachorro, MultipartFile imagem) {
        if (imagem != null && !imagem.isEmpty()) {
            String nomeArquivo = fileStorageService.store(imagem);
            cachorro.setFotoUrl(nomeArquivo);
        }
        cachorroRepository.save(cachorro);
    }

    @Override
    @PreAuthorize("@ongSecurityService.podeGerenciarCao(authentication, #id)")
    public void deletarPorId(Long id) {
        cachorroRepository.deleteById(id);
    }

    @Override
    public List<Cachorro> buscarPorOng(ONG ong) {
        return cachorroRepository.findAllByOng(ong);
    }
}