package br.com.fabioperettig.services.generic;

import br.com.fabioperettig.dao.Persistente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericService <T extends Persistente, E extends Serializable> {

    public Boolean createService(T entity) throws TipoChaveNaoEncontradaException;
    public T readService(E value);
    public void update(T entity) throws TipoChaveNaoEncontradaException;
    public void delete(E value);

    public Collection<T> findAll();

}
