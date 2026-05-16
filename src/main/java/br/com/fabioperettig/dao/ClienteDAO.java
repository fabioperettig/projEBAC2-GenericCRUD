package br.com.fabioperettig.dao;

import br.com.fabioperettig.dao.generic.GenericDAO;
import br.com.fabioperettig.domain.Cliente;

public class ClienteDAO extends GenericDAO<Cliente, Long> implements  IClienteDAO {

    public ClienteDAO(){
        super();
    }

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualizarDados(Cliente entityNova, Cliente entityAntiga) {
        entityAntiga.setNome(entityNova.getNome());
        entityAntiga.setCpf(entityNova.getCpf());
        entityAntiga.setEmail(entityNova.getEmail());
    }
}
