package br.com.fabioperettig.services;

import br.com.fabioperettig.dao.generic.IGenericDAO;
import br.com.fabioperettig.domain.Produto;
import br.com.fabioperettig.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService{

    public ProdutoService(IGenericDAO<Produto, String> dao) {
        super(dao);
    }

}
