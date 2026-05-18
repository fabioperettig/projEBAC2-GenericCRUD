package br.com.fabioperettig.services.generic;

import br.com.fabioperettig.dao.Persistente;
import br.com.fabioperettig.dao.generic.IGenericDAO;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;


public abstract class GenericService <T extends Persistente, E extends Serializable> implements IGenericService<T,E>{

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T, E> dao) {
        this.dao = dao;
    }

    @Override
    public Boolean createService(T entity) throws TipoChaveNaoEncontradaException {
        return this.dao.create(entity);
    }

    @Override
    public T readService(E value) {
        return this.dao.read(value);
    }

    @Override
    public void update(T entity) throws TipoChaveNaoEncontradaException {
        this.dao.update(entity);
    }

    @Override
    public void delete(E value) {
        this.dao.delete(value);
    }

    @Override
    public Collection<T> findAll() {
        return this.dao.findAll();
    }
}
