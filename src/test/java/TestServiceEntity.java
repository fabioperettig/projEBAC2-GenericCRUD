import br.com.fabioperettig.dao.IClienteDAO;
import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;
import br.com.fabioperettig.services.ClienteService;
import br.com.fabioperettig.services.generic.IGenericService;
import mockDAO.ClienteDAOMock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestServiceEntity {

    private IGenericService iGenericService;
    private IClienteDAO iGenDAO;

    @BeforeEach
    void init(){
        iGenDAO = new ClienteDAOMock();
        iGenericService = new ClienteService(iGenDAO);
    }

    @Test
    public void testCreateService() throws TipoChaveNaoEncontradaException {
        Cliente cliente = new Cliente("testService", 123L, "tServ");
        iGenericService.createService(cliente);

        Assertions.assertEquals(123L, cliente.getCpf());
    }

    @Test
    public void testReadService() throws TipoChaveNaoEncontradaException {
        Cliente cliente = new Cliente("testService", 123L, "tServ");
        iGenericService.createService(cliente);

        Cliente clienteLido = (Cliente) iGenericService.readService(cliente.getCpf());

        Assertions.assertNotNull(clienteLido);
        Assertions.assertEquals(cliente.getCpf(), clienteLido.getCpf());
        Assertions.assertEquals(cliente.getNome(), clienteLido.getNome());
    }

    @Test
    public void testUpdateService() throws TipoChaveNaoEncontradaException{
        Cliente test1 = new Cliente("clienteUP", 123L, "tRead");
        iGenericService.update(test1);

        Assertions.assertNotNull(test1);
    }
}
