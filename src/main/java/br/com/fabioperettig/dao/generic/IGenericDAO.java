package br.com.fabioperettig.dao.generic;

import br.com.fabioperettig.dao.Persistente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO <T extends Persistente, E extends Serializable> {

    /// generic CRUD methods

    public Boolean create(T entity) throws TipoChaveNaoEncontradaException;
    public T read(E value);
    public void update(T entity) throws TipoChaveNaoEncontradaException;
    public void delete(E value);
    public Collection<T> findAll();

}
