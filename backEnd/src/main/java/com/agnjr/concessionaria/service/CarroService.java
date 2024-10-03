package com.agnjr.concessionaria.service;

import com.agnjr.concessionaria.model.Carro;
import com.agnjr.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarroService implements GenericService<Carro> {

    //Ativida anterior:
    //private Map<Long, Carro> carroMap = new HashMap<>();

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public void incluir(Carro carro) {
        carroRepository.save(carro);
    }

    @Override
    public void excluir(Long id) {
        carroRepository.deleteById(id);
    }

    @Override
    public Collection<Carro> obterLista() {
        return carroRepository.findAll();
    }

    @Override
    public Carro obterPorId(Long id) {
        Optional<Carro> carroOpt = carroRepository.findById(id);
        return carroOpt.orElse(null);
    }

    public Collection<Carro> buscarPorFabricante(String fabricante) {
        return carroRepository.findByFabricante(fabricante);
    }

    public List<Carro> obterListaComOrdenacao(Sort sort) {
        return carroRepository.findAll(sort);
    }
}