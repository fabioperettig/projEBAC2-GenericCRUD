package br.com.fabioperettig.dao.generic;

import java.util.HashMap;
import java.util.Map;

public class SingletonMap {

    private static SingletonMap singletonMap;

    /// simula o banco de dados e faz cada entidade (produto, cliente)
    /// ter seu próprio map analisando o Generic.
    protected Map<Class, Map<?,?>> map;

    private SingletonMap(){
        map = new HashMap<>();
    }

    /// metodo que garante o retorno apenas da INSTÂNCIA do map
    public static SingletonMap getInstance(){
        if (singletonMap == null) {
            singletonMap = new SingletonMap();
        }
        return singletonMap;
    }

    public Map<Class, Map<?,?>> getMap(){
        return this.map;
    }

}
