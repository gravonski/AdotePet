package com.andreigravonski.adotepet.repository;

import com.andreigravonski.adotepet.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    Long id(Long id);
}
