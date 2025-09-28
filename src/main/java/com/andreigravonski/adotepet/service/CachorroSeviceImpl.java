package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;
import java.util.List;


@Service
public class CachorroSeviceImpl implements CachorroService{

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public List<Cachorro> buscarTodos() {
        return cachorroRepository.findAll();
    }

    @Override
    @PreAuthorize("@ongSecurityService.podeGerenciarCao(authentication, #id)")
    public Cachorro buscarPorId(Long id) {
        return cachorroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cão não encontrado"));
    }

    @Override
    @PreAuthorize("#cachorro.id == null or @ongSecurityService.podeGerenciarCao(authentication, #cachorro.id)")
    public void salvar(Cachorro cachorro) {
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

    @Autowired
    private CachorroRepository cachorroRepository;
}
