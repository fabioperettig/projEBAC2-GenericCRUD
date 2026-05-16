package br.com.fabioperettig.services;

import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

public interface IClienteService {

    Boolean createServC(Cliente cliente) throws TipoChaveNaoEncontradaException;
    Cliente readServC(Long cpf);
    void updateServC(Cliente cliente) throws  TipoChaveNaoEncontradaException;
    void deleteServC(Long cpf);

}
