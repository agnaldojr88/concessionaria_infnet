package com.agnjr.concessionaria.service;

import com.agnjr.concessionaria.model.Moto;
import com.agnjr.concessionaria.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MotoService implements GenericService<Moto> {

    //Ativida anterior:
    //private Map<Long, Moto> motoMap = new HashMap<>();

    @Autowired
    private MotoRepository motoRepository;

    @Override
    public void incluir(Moto moto) {
        motoRepository.save(moto);
    }

    @Override
    public void excluir(Long id) {
        motoRepository.deleteById(id);
    }

    @Override
    public Collection<Moto> obterLista() {
        return motoRepository.findAll();
    }

    @Override
    public Moto obterPorId(Long id) {
        Optional<Moto> motoOpt = motoRepository.findById(id);
        return motoOpt.orElse(null);
    }

    public Collection<Moto> buscarPorCilindradasMinimas(Integer cilindradas) {
        return motoRepository.findByCilindradasGreaterThanEqual(cilindradas);
    }
}