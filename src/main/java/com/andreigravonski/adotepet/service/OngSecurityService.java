package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.CachorroRepository;
import com.andreigravonski.adotepet.repository.ONGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service("ongSecurityService")
public class OngSecurityService {

    @Autowired
    private CachorroRepository cachorroRepository;

    @Autowired
    private ONGRepository ongRepository;

    public boolean podeGerenciarCao(Authentication authentication, Long id) {

        String emailLogado = authentication.getName();

        ONG ongLogada = ongRepository.findByEmail(emailLogado);
        if (ongLogada == null) {
            return false;
        }

        Optional<Cachorro> optionalCachorro = cachorroRepository.findById(id);

        if (optionalCachorro.isEmpty()) {
            return false;
        }

        Cachorro cachorro = optionalCachorro.get();
        Long idDono = cachorro.getOng().getId();


        return ongLogada.getId().equals(idDono);
    }
}
