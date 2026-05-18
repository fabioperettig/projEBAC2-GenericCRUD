import br.com.fabioperettig.dao.IClienteDAO;
import br.com.fabioperettig.dao.IProdutoDAO;
import br.com.fabioperettig.dao.IVenda;
import br.com.fabioperettig.dao.VendaDAO;
import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.domain.Produto;
import br.com.fabioperettig.domain.Venda;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;
import mockDAO.ClienteDAOMock;
import mockDAO.ProdutoDAOMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class TestVendaDAO {

    private IVenda vendaDAO;
    private IClienteDAO clienteDAO;
    private IProdutoDAO produtoDAO;

    private Cliente cliente;
    private Produto produto;

    public TestVendaDAO() {
        vendaDAO = new VendaDAO();
        clienteDAO = new ClienteDAOMock();
        produtoDAO = new ProdutoDAOMock();
    }

    @BeforeEach
    public void init() {
        this.cliente = createCliente();
        this.produto = createProduto();
    }

    @Test
    public void testVendaCodigo() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("VND2601");
        Boolean result = vendaDAO.create(venda);

        Assertions.assertTrue(result);
        Assertions.assertEquals("VND2601", venda.getCodigo());
    }

    @Test
    public void testVendaCodigoCTOR() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("NEW_CODE");
        vendaDAO.create(venda);

        Assertions.assertEquals("NEW_CODE", venda.getCodigo());
    }

    @Test
    public void vendaIniciada() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("VND26AA");
        Boolean result = vendaDAO.create(venda);

        Assertions.assertTrue(result);
        Assertions.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void vendaCancelada() throws  TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("VND26BB");
        vendaDAO.create(venda);
        venda.setStatus(Venda.Status.CANCELADA);

        Assertions.assertNotEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void vendaAlterarQtd() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("VND26BB");
        vendaDAO.create(venda);
        venda.addProduto(produto, 10);

        Assertions.assertEquals(110, venda.getQtdTotal());
    }

    private Cliente createCliente() {
        cliente = new Cliente("Cliente", 123L, "cl");
        return cliente;
    }

    private Produto createProduto() {
        produto = new Produto("123H","prod1", BigDecimal.ONE);
        return produto;
    }

    private Venda criarVenda(String codigo) {
        Venda venda = new Venda();

        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setStatus(Venda.Status.INICIADA);
        venda.setCliente(this.cliente);
        venda.addProduto(this.produto, 100);
        return venda;
    }

}
