package com.agnjr.concessionaria.service;

import com.agnjr.concessionaria.model.Caminhao;
import com.agnjr.concessionaria.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CaminhaoService implements GenericService<Caminhao> {

    //Ativida anterior:
    //private Map<Long, Caminhao> caminhaoMap = new HashMap<>();

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    @Override
    public void incluir(Caminhao caminhao) {
        caminhaoRepository.save(caminhao);
    }

    @Override
    public void excluir(Long id) {
        caminhaoRepository.deleteById(id);
    }

    @Override
    public Collection<Caminhao> obterLista() {
        return caminhaoRepository.findAll();
    }

    @Override
    public Caminhao obterPorId(Long id) {
        Optional<Caminhao> caminhaoOpt = caminhaoRepository.findById(id);
        return caminhaoOpt.orElse(null);
    }

    public Collection<Caminhao> buscarPorTipoCabine(Integer tipoCabine) {
        return caminhaoRepository.findByTipoCabine(tipoCabine);
    }
}