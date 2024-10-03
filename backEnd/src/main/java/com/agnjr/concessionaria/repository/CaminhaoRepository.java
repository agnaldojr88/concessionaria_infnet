package com.agnjr.concessionaria.repository;

import com.agnjr.concessionaria.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
    List<Caminhao> findByTipoCabine(Integer tipoCabine);
}