package com.agnjr.concessionaria.service;

import com.agnjr.concessionaria.model.Veiculo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class VeiculoService implements GenericService<Veiculo> {

    private Map<Long, Veiculo> veiculoMap = new HashMap<>();

    @Override
    public void incluir(Veiculo veiculo) {
        veiculoMap.put(veiculo.getId(), veiculo);
    }

    @Override
    public void excluir(Long id) {
        veiculoMap.remove(id);
    }

    @Override
    public Collection<Veiculo> obterLista() {
        return veiculoMap.values();
    }

    public Veiculo obterPorId(Long id) {
        return veiculoMap.get(id);
    }
}