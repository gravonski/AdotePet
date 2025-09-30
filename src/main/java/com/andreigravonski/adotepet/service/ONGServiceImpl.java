package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.exception.EmailJaCadastradoExcepition;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.ONGRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.andreigravonski.adotepet.repository.RoleRepository;
import com.andreigravonski.adotepet.model.Role;
import com.andreigravonski.adotepet.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

import java.util.List;

@Slf4j
@Service
public class ONGServiceImpl implements ONGService {

    private final ONGRepository ongRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public ONGServiceImpl(ONGRepository ongRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.ongRepository = ongRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<ONG> buscarONG() {
        return ongRepository.findAll();
    }

    @Override
    public ONG buscarPorId(Long id) {
        return ongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ONG nao encontrada!"));
    }

    @Override
    @PreAuthorize("#ong.id == null or @ongSecurityService.podeGerenciarOng(authentication, #ong.id)")
    public void salvarONG(ONG ong) {
        ongRepository.save(ong);
    }

    @Override
    @PreAuthorize("@ongSecurityService.podeGerenciarOng(authentication, #id)")
    public void deletarPorId(Long id) {
        ongRepository.deleteById(id);
    }

    @Override
    public void registrarNovaOng(ONG ong) {
        if (ongRepository.findByEmail(ong.getEmail()) != null) {
            throw new EmailJaCadastradoExcepition("Esse EMAIL já está em uso.");
        }

        String senhaCriptografada = passwordEncoder.encode(ong.getPassword());
        ong.setSenha(senhaCriptografada);

        Role ongRole = roleRepository.findByNome("ROLE_ONG");
        ong.setRoles(Set.of(ongRole));

        ongRepository.save(ong);
    }

}
