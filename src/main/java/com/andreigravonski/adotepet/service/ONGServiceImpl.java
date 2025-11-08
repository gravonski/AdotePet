package com.andreigravonski.adotepet.service;

import com.andreigravonski.adotepet.exception.EmailJaCadastradoExcepition;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.repository.ONGRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.andreigravonski.adotepet.repository.RoleRepository;
import com.andreigravonski.adotepet.model.Role;


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

    /// Lembre-se dos imports para Role, Set, etc.

    @Override
    @Transactional // Garante que a operação seja atômica
    public void criarAdminUserSeNaoExistir() {
        // 1. Verifica se o admin já existe
        if (ongRepository.findByEmail("admin@adotepet.com") == null) {
            System.out.println("---[FORJA ATIVA] Criando usuário admin...---");

            // 2. Busca o ROLE_ADMIN. Se não existir, cria.
            Role adminRole = roleRepository.findByNome("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setNome("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            // 3. Busca o ROLE_ONG. Se não existir, cria.
            Role ongRole = roleRepository.findByNome("ROLE_ONG");
            if (ongRole == null) {
                ongRole = new Role();
                ongRole.setNome("ROLE_ONG");
                roleRepository.save(ongRole);
            }

            // 4. Criptografa a senha "senha123" na hora
            String senhaCriptografada = passwordEncoder.encode("senha123");

            // 5. Cria a nova ONG admin
            ONG adminOng = new ONG();
            adminOng.setNome("ONG Admin");
            adminOng.setEmail("admin@adotepet.com");
            adminOng.setSenha(senhaCriptografada);
            adminOng.setRoles(Set.of(adminRole, ongRole)); // Dá ambos os papéis ao admin

            // 6. Salva o admin no banco
            ongRepository.save(adminOng);
            System.out.println("---[FORJA ATIVA] Usuário admin criado com sucesso.---");
        } else {
            System.out.println("---[FORJA ATIVA] Usuário admin já existe. Nenhuma ação necessária.---");
        }
    }

}
