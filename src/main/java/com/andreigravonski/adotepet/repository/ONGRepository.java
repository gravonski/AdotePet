package com.andreigravonski.adotepet.repository;

import com.andreigravonski.adotepet.model.ONG;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ONGRepository extends JpaRepository<ONG, Long> {
    ONG findByEmail(String email);
}
