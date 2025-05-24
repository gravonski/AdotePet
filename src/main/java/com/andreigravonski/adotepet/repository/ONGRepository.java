package com.andreigravonski.adotepet.repository;

import com.andreigravonski.adotepet.model.ONG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ONGRepository extends JpaRepository <ONG, Long> {
}
