package com.andreigravonski.adotepet.repository;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.ONG;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CachorroRepository extends JpaRepository<Cachorro, Long> {
    List<Cachorro> findAllByOng(ONG ong);
}
