package com.agnjr.concessionaria.service;

import java.util.Collection;

public interface GenericService<T> {

    //Ao invés de criar uma interface para cada service, foi criado uma Generica

    void incluir(T objeto);

    void excluir(Long id);

    Collection<T> obterLista();

    T obterPorId(Long id);

}