package br.com.fabioperettig.dao;

import br.com.fabioperettig.dao.generic.GenericDAO;
import br.com.fabioperettig.domain.Produto;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO {

    public ProdutoDAO() {
        super();
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualizarDados(Produto entityNova, Produto entityAntiga) {

        entityAntiga.setNome(entityNova.getNome());
        entityAntiga.setCodigo(entityNova.getCodigo());
        entityAntiga.setValor(entityNova.getValor());

    }

}
