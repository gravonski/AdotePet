package com.andreigravonski.adotepet.repository;

import com.andreigravonski.adotepet.model.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CachorroRepository extends JpaRepository<Cachorro, Long> {
}
