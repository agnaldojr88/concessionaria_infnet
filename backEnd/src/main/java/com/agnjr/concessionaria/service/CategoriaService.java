package com.agnjr.concessionaria.service;

import com.agnjr.concessionaria.model.Categoria;
import com.agnjr.concessionaria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoriaService implements GenericService<Categoria> {

    //Ativida anterior:
    //private Map<Long, Categoria> categoriaMap = new HashMap<>();

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public void incluir(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Collection<Categoria> obterLista() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obterPorId(Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        return categoriaOpt.orElse(null);
    }
}