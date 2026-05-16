package mockDAO;

import br.com.fabioperettig.dao.IProdutoDAO;
import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.domain.Produto;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoDAOMock implements IProdutoDAO {

    private Map<Long, Produto> productMockMap = new HashMap<>();

    /// generic CRUD methods
    @Override
    public Boolean create(Produto entity) {
        if (productMockMap.containsKey(entity.getCodigo())){
            return false;
        }

        productMockMap.put(Long.valueOf(entity.getCodigo()), entity);
        return true;
    }

    @Override
    public Produto read(String value) {
        System.out.println("read Produto DAO concluído.");
        return productMockMap.get(Long.valueOf(value));
    }

    @Override
    public void update(Produto entity) { }

    @Override
    public void delete(String value) {
        System.out.println("delete Produto DAO concluído.");
    }

    @Override
    public Collection<Produto> findAll() {
        return List.of();
    }

    public Map<Long, Produto> getProductMockMap() {
        System.out.println("Busca de Map concluída.");
        return productMockMap;
    }
}
