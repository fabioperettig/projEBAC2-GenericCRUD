import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;
import mockDAO.ClienteDAOMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fabioperettig.dao.IClienteDAO;
import br.com.fabioperettig.dao.ClienteDAO;

import java.util.Map;

public class TestDAOCliente {

    private IClienteDAO iClienteDAO;

    @BeforeEach
    void init() {
        iClienteDAO = new ClienteDAOMock();
    }

    @Test
    public void testCreateClient() throws TipoChaveNaoEncontradaException {
        Cliente test1 = new Cliente("testCREATE", 123L, "tCreate");

        iClienteDAO.create(test1);
        Assertions.assertEquals("tCreate@mail", test1.getEmail());
    }

    @Test
    public void testReadClient() throws TipoChaveNaoEncontradaException {
        Cliente test1 = new Cliente("testREAD", 123L, "tRead");
        iClienteDAO.create(test1);

        Cliente readClient = iClienteDAO.read(test1.getCpf());
        Assertions.assertEquals(test1.getCpf(), readClient.getCpf());
    }

    @Test
    public void testUpdateClient() throws TipoChaveNaoEncontradaException {
        ClienteDAO dao = new ClienteDAO();
        Cliente test1 = new Cliente("clienteOLD", 123L, "tRead");
        Cliente test2 = new Cliente("testREAD", 1345L, "tRead");

        iClienteDAO.create(test1);
        dao.atualizarDados(test2, test1);

        Assertions.assertNotEquals("clienteOLD",test1.getNome());
        Assertions.assertEquals("testREAD",test1.getNome());
    }

    @Test
    public void testDeleteClient() throws TipoChaveNaoEncontradaException {
        ClienteDAOMock daoMock = new ClienteDAOMock();
        Cliente test1 = new Cliente("clienteDelete", 123L, "tRead");
        iClienteDAO.create(test1);

        Map<Long, Cliente> mockMap = daoMock.getMockMap();
        iClienteDAO.delete(123L);

        Assertions.assertTrue(mockMap.isEmpty());
    }

}
