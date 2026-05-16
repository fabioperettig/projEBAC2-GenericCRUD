package br.com.fabioperettig.dao;

import br.com.fabioperettig.dao.generic.GenericDAO;
import br.com.fabioperettig.domain.Venda;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

public class VendaDAO extends GenericDAO<Venda, String> implements IVenda {


    @Override
    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException {
        venda.setStatus(Venda.Status.CONLCUIDA);
        super.update(venda);
    }

    @Override
    public Class<Venda> getTipoClasse() {
        return Venda.class;
    }

    @Override
    public void atualizarDados(Venda entity, Venda entityCadastrada) {
        entityCadastrada.setCodigo(entity.getCodigo());
        entityCadastrada.setStatus(entity.getStatus());
    }
}
