package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.ONGRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OngUserDetailsService implements UserDetailsService {
    private final ONGRepository ongRepository;

    public OngUserDetailsService(ONGRepository ongRepository) {
        this.ongRepository = ongRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("---[Security Debug] Buscando usuário pelo email: " + username); // ESCUTA 1

        ONG ong = ongRepository.findByEmail(username);

        if (ong == null) {
            System.out.println("---[Security Debug] Usuário NÃO encontrado na base de dados."); // ESCUTA 2
            throw new UsernameNotFoundException("Usuário com email " + username + " não encontrado.");
        }

        System.out.println("---[Security Debug] Usuário encontrado: " + ong.getEmail()); // ESCUTA 3
        return ong;
    }
}
