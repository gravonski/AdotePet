package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cachorro cachorro = cachorroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cão de ID " + id + " não encontrado!"));
        Long IdDono = cachorro.getOng().getId();
        if (!authentication.getName().equals("user") ||  !IdDono.equals(1L)) {
            throw new AccessDeniedException ("Este usuário não pode altetar esse cão");
        } else {
            cachorroRepository.deleteById(id);
        }
    }

    @Autowired
    private CachorroRepository cachorroRepository;
}
