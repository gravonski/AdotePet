package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.andreigravonski.adotepet.specification.CachorroSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class CachorroServiceImpl implements CachorroService {

    private final CachorroRepository cachorroRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public CachorroServiceImpl(CachorroRepository cachorroRepository, FileStorageService fileStorageService) {
        this.cachorroRepository = cachorroRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<Cachorro> buscarTodos() {
        return cachorroRepository.findAll();
    }

    @Override
    public Cachorro buscarPorId(Long id) {
        return cachorroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cão de ID " + id + " não encontrado!"));
    }

    @Override
    public List<Cachorro> buscarCaesFiltrado(ONG ong, String raca) {
        // 1. Começa com uma query "vazia" que seleciona tudo.
        Specification<Cachorro> spec = Specification.where(null);

        // 2. Adiciona o primeiro "bloco de Lego": filtrar pela ONG logada.
        // (Precisamos criar este bloco em CachorroSpecification)
        spec = spec.and(CachorroSpecification.porOng(ong));

        // 3. Se o usuário forneceu uma raça...
        if (raca != null && !raca.isBlank()) {
            // ...encaixe o "bloco de Lego" da raça.
            spec = spec.and(CachorroSpecification.porRaca(raca));
        }

        // (Amanhã, se o usuário mandar idade, adicionaremos .and(CachorroSpecification.porIdade(idade)))

        // 4. Executa a query dinâmica montada.
        return cachorroRepository.findAll(spec);
    }

    @Override
    public Cachorro salvar(Cachorro cachorro, MultipartFile imagemFile) { // <-- Assinatura correta
        if (imagemFile != null && !imagemFile.isEmpty()) {
            String nomeArquivo = fileStorageService.store(imagemFile);
            cachorro.setFotoUrl(nomeArquivo);
        }
        return cachorroRepository.save(cachorro);
    }

    @Override
    public void deletarPorId(Long id) {
        cachorroRepository.deleteById(id);
    }

    @Override
    public List<Cachorro> buscarPorOng(ONG ong) {
        return cachorroRepository.findAllByOng(ong);
    }
}