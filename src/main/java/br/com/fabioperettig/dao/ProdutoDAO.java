package br.com.fabioperettig.dao;

import br.com.fabioperettig.dao.generic.GenericDAO;
import br.com.fabioperettig.domain.Produto;

public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO {

    public ProdutoDAO() {
        super();
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualizarDados(Produto entity, Produto entityCadastrada) {

        entityCadastrada.setNome(entity.getNome());
        entityCadastrada.setCodigo(entity.getCodigo());
        entityCadastrada.setValor(entity.getValor());

    }
}
