import br.com.fabioperettig.dao.ClienteDAO;
import br.com.fabioperettig.dao.IProdutoDAO;
import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.domain.Produto;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;
import mockDAO.ClienteDAOMock;
import mockDAO.ProdutoDAOMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

public class TestDAOProduto {

    private IProdutoDAO iProdutoDAO;

    @BeforeEach
    void init(){
        iProdutoDAO = new ProdutoDAOMock();
    }

    @Test
    public void testCreateProduto() throws TipoChaveNaoEncontradaException {
        Produto p0101 = new Produto("010101", "prod1", BigDecimal.ONE);

        iProdutoDAO.create(p0101);
        Assertions.assertEquals("010101", p0101.getCodigo());
    }

    @Test
    public void testReadProduto() throws TipoChaveNaoEncontradaException {
        Produto p0102 = new Produto("020202", "prod2", BigDecimal.TEN);
        iProdutoDAO.create(p0102);

        Produto readProd = iProdutoDAO.read(p0102.getCodigo());
        Assertions.assertEquals(readProd.getCodigo(), p0102.getCodigo());
    }


}
