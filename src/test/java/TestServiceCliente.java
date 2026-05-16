import br.com.fabioperettig.dao.IClienteDAO;
import br.com.fabioperettig.domain.Cliente;
import br.com.fabioperettig.exceptions.TipoChaveNaoEncontradaException;
import br.com.fabioperettig.services.ClienteService;
import br.com.fabioperettig.services.IClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestServiceCliente {

    private IClienteService iClienteService;
    private IClienteDAO iClienteDAO;

    @BeforeEach
    void init(){
        iClienteService = new ClienteService(iClienteDAO);
    }

    @Test
    public void testCreateService() throws TipoChaveNaoEncontradaException {
        Cliente cliente = new Cliente("testService", 123L, "tServ");

        iClienteService.createServC(cliente);

        Assertions.assertEquals(123L, cliente.getCpf());
    }

}
