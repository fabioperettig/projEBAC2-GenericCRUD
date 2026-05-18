import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Test Suite")
@SelectClasses({
        TestDAOCliente.class,
        TestDAOProduto.class,
        TestServiceEntity.class,
        TestVendaDAO.class
})
public class TestSuite {
}
