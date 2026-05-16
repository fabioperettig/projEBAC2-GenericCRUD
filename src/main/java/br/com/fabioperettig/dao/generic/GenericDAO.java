package br.com.fabioperettig.dao.generic;

import annotation.TipoChave;
import br.com.fabioperettig.dao.Persistente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDAO <T extends Persistente, E extends Serializable> implements IGenericDAO<T,E>{

    private SingletonMap singletonMap;
    public abstract Class<T> getTipoClasse();
    public abstract void atualizarDados(T entity, T entityCadastrada);

    /// DI
    public GenericDAO(){
        this.singletonMap = SingletonMap.getInstance();
    }

    public E getChave(T entity) throws TipoChaveNaoEncontradaException{
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;

        for (Field field : fields){

            if(field.isAnnotationPresent(TipoChave.class)){
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                String nomeMetodo = tipoChave.value();

                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new TipoChaveNaoEncontradaException("Chave principal do objeto "
                            + entity.getClass() + " não encontrada", e);
                }
            }
        }

        if (returnValue == null) {
            String msg = "Chave principal do objeto " + entity.getClass() + " não encontrada";
            System.out.println("***** ERRO *****" + msg);
            throw new TipoChaveNaoEncontradaException(msg);
        }
        return null;
    }

    /// Cria o mapa (mapaInterno) de cada Classe (Cliente, Produto, etc) com singleton
    private Map<E, T> getMapa(){

        Map<E, T> mapaInterno = (Map<E, T>) this.singletonMap.getMap().get(getTipoClasse());

        if (mapaInterno == null){
            mapaInterno = new HashMap<>();
            this.singletonMap.getMap().put(getTipoClasse(), mapaInterno);
        }

        return mapaInterno;

    }

    /// CRUD Methods
    @Override
    public Boolean create(T entity) throws TipoChaveNaoEncontradaException {
        Map<E,T> mapaInterno = getMapa();
        E chave = getChave(entity);

        if(mapaInterno.containsKey(chave)){
            return false;
        }

        mapaInterno.put(chave, entity);
        return true;
    }

    @Override
    public T read(E value) {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.get(value);
    }

    @Override
    public void update(T entity) throws TipoChaveNaoEncontradaException {
        Map<E, T> mapaInterno = getMapa();
        E chave = getChave(entity);
        T objetoCadastrado = mapaInterno.get(chave);

        if (objetoCadastrado != null){
            atualizarDados(entity,objetoCadastrado);
        }
    }

    @Override
    public void delete(E value) {
        Map<E, T> mapaInterno = getMapa();
        T objetoCadastrado = mapaInterno.get(value);

        if (objetoCadastrado != null){
            mapaInterno.remove(value,objetoCadastrado);
        }
    }

    @Override
    public Collection<T> findAll() {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.values();
    }
}
