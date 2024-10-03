package com.agnjr.concessionaria.repository;

import com.agnjr.concessionaria.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    List<Moto> findByCilindradasGreaterThanEqual(Integer cilindradas);
}