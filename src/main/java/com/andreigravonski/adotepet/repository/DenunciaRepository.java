package com.andreigravonski.adotepet.repository;

import com.andreigravonski.adotepet.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DenunciaRepository extends JpaRepository<Denuncia, Long> {
}
