package mockDAO;

import br.com.fabioperettig.dao.IClienteDAO;
import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteDAOMock implements  IClienteDAO {

    private Map<Long, Cliente> mockMap = new HashMap<>();

    /// generic CRUD methods
    @Override
    public Boolean create(Cliente entity) {
        if (mockMap.containsKey(entity.getCpf())){
            return false;
        }

        mockMap.put(entity.getCpf(), entity);
        System.out.println("create DAO concluído.");

        return true;
    }

    @Override
    public Cliente read(Long value) {
        System.out.println("read DAO concluído.");
        return mockMap.get(value);
    }

    @Override
    public void update(Cliente entity) { }

    @Override
    public void delete(Long value) {
        System.out.println("delete DAO concluído.");
    }

    @Override
    public Collection<Cliente> findAll() {
        return List.of();
    }

    public Map<Long, Cliente> getMockMap() {
        System.out.println("Busca de Map concluída.");
        return mockMap;
    }
}
