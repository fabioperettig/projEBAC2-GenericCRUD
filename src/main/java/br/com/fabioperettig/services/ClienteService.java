package br.com.fabioperettig.services;

import br.com.fabioperettig.dao.generic.IGenericDAO;
import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;
import br.com.fabioperettig.services.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

    public ClienteService(IGenericDAO<Cliente, Long> dao) {
        super(dao);
    }

    @Override
    public Boolean createServC(Cliente cliente) throws TipoChaveNaoEncontradaException {
        return this.dao.create(cliente);
    }

    @Override
    public Cliente readServC(Long cpf) {
        return this.dao.read(cpf);
    }

    @Override
    public void updateServC(Cliente cliente) throws TipoChaveNaoEncontradaException {
        this.dao.update(cliente);
    }

    @Override
    public void deleteServC(Long cpf) {
        this.dao.delete(cpf);
    }
}
