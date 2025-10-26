package com.andreigravonski.adotepet.repository;

import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.model.StatusDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andreigravonski.adotepet.model.ONG;
import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    Long id(Long id);
    long countByStatus(StatusDenuncia status);
    List<Denuncia> findByOngIsNullAndStatus(StatusDenuncia status);
    List<Denuncia> findByOng(ONG ong);
}
