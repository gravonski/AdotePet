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
        ONG ong = ongRepository.findByEmail(username);

        if (ong == null) {
            throw new UsernameNotFoundException("Usuário de email " + username + " não encontrado.");
        }

        return ong;
    }
}
