![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Projeto Curso EBAC](https://img.shields.io/badge/Projeto--Curso--EBAC-navy?style=for-the-badge)
![JUnit5](https://img.shields.io/badge/JUnit5-f5f5f5?style=for-the-badge&logo=junit5&logoColor=dc524a)

# ☕ Projeto EBAC2 - GenericCRUD

Este projeto é uma revisita do primeiro projeto do curso [Cadastro e gerenciamento de dados com arquitetura CRUD](https://github.com/fabioperettig/projEBAC1-ClientCRUD), utilizando o padrão DAO (Data Access Object), **com o diferencial** no uso de Generics para construção de uma estrutura dinâmica, reutilizável e de baixo acoplamento.

O grande diferencial do projeto é a implementação de uma camada genérica abstrata, responsável por centralizar as operações CRUD e permitir que **diferentes entidades reutilizem a mesma lógica base**, reduzindo repetição de código e facilitando a manutenção da aplicação.


<details><summary><b>Classe DAO Contrato</b></summary>

````java
package br.com.fabioperettig.dao.generic;

public abstract class GenericDAO <T extends Persistente, E extends Serializable> implements IGenericDAO<T,E>{

    private SingletonMap singletonMap;
    public abstract Class<T> getTipoClasse();
    public abstract void atualizarDados(T entity, T entityCadastrada);

    /// DI
    public GenericDAO(){
        this.singletonMap = SingletonMap.getInstance();
    }

    public E getChave(T entity) throws TipoChaveNaoEncontradaException{
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;

        for (Field field : fields){

            if(field.isAnnotationPresent(TipoChave.class)){
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                String nomeMetodo = tipoChave.value();

                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new TipoChaveNaoEncontradaException("Chave principal do objeto "
                            + entity.getClass() + " não encontrada", e);
                }
            }
        }

        if (returnValue == null) {
            String msg = "Chave principal do objeto " + entity.getClass() + " não encontrada";
            System.out.println("***** ERRO *****" + msg);
            throw new TipoChaveNaoEncontradaException(msg);
        }
        return null;
    }

    /// Cria o mapa (mapaInterno) de cada Classe (Cliente, Produto, etc.) com singleton
    private Map<E, T> getMapa(){

        Map<E, T> mapaInterno = (Map<E, T>) this.singletonMap.getMap().get(getTipoClasse());

        if (mapaInterno == null){
            mapaInterno = new HashMap<>();
            this.singletonMap.getMap().put(getTipoClasse(), mapaInterno);
        }

        return mapaInterno;
    }

    /// CRUD Methods
    @Override
    public Boolean create(T entity) throws TipoChaveNaoEncontradaException {
        Map<E,T> mapaInterno = getMapa();
        E chave = getChave(entity);

        if(mapaInterno.containsKey(chave)){
            return false;
        }

        mapaInterno.put(chave, entity);
        return true;
    }

    @Override
    public T read(E value) {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.get(value);
    }

    @Override
    public void update(T entity) throws TipoChaveNaoEncontradaException {
        Map<E, T> mapaInterno = getMapa();
        E chave = getChave(entity);
        T objetoCadastrado = mapaInterno.get(chave);

        if (objetoCadastrado != null){
            atualizarDados(entity,objetoCadastrado);
        }
    }

    @Override
    public void delete(E value) {
        Map<E, T> mapaInterno = getMapa();
        T objetoCadastrado = mapaInterno.get(value);

        if (objetoCadastrado != null){
            mapaInterno.remove(value,objetoCadastrado);
        }
    }

    @Override
    public Collection<T> findAll() {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.values();
    }
}
````
</details>

<br>

## ⚙️ Arquitetura

A arquitetura do projeto foi organizada em camadas, separando responsabilidades:
* **dao →** persistência e manipulação de dados
* **domain →** entidades do sistema
* **services →** regras de negócio

````
Java
├── dao
|	├── generic
|	|   ├── IGenericDAO
|	|   ├── GenericDAO
|	|   └── SingletonMap
|	|
|	├── IClienteDAO
|	├── IProdutoDAO
|	├── IVenda
|	├── ClienteDAO
|	├── ProdutoDAO
|	├── ProdutoDAO
|	└── Persistente
|
├── domain
|	├── Cliente
|	├── Produto
|	├── ProdutoQtd
|	└── Venda
|
├── service
|	├── generic
|	|   ├── IGenericService
|	|   └── GenericService
|	|
|	├── IClienteService
|	├── IProdutoService
|	├── ClienteService
|	└── ProdutoService
|
Test
````
## ✅ Testes

Para garantir que todos os métodos funcionem corretamente, o projeto conta com Classes de Testes, focadas em comportamentos específicos. Além disso, o testes foram feitos sob Classes 'Mock' que continham retornos específicos para teste.

<details><summary><b>Classe ClienteDAOMock</b></summary>

````java
package mockDAO;

public class ClienteDAOMock implements  IClienteDAO {

    private Map<Long, Cliente> mockMap = new HashMap<>();

    /// generic CRUD methods
    @Override
    public Boolean create(Cliente entity) {
        if (mockMap.containsKey(entity.getCpf())){
            return false;
        }

        mockMap.put(entity.getCpf(), entity);
        System.out.println("create DAO concluído.");

        return true;
    }

    @Override
    public Cliente read(Long value) {
        System.out.println("read DAO concluído.");
        return mockMap.get(value);
    }

    @Override
    public void update(Cliente entity) { }

    @Override
    public void delete(Long value) {
        System.out.println("delete DAO concluído.");
    }

    @Override
    public Collection<Cliente> findAll() {
        return List.of();
    }

    public Map<Long, Cliente> getMockMap() {
        System.out.println("Busca de Map concluída.");
        return mockMap;
    }
}
````
</details>

## ⚙️ Estrutura classe Testes

````java
Test
├── mockDAO
|	├── ClienteDAOMock
|	└── ProdutoDAOMock
|	
├── TestDAOCliente
├── TestDAOProduto
├── TestServiceEntity
├── TestVendaDAO
└── TestSuite
````

Para tornar todo o processo de testes mais dinâmico, também foi implementada uma Classe de Testes Suite, agregando todas as classes de testes em um processo de checagem única.

````java
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
````
## 📚 Junit 5
Atualmente, a versão mais recomendada do framweork JUnit é a versão 5, diferente da versão usada no curso. Então alguns, operadores estarão diferentes, apesar de alcançarem o mesmo objetivo.

````XML
<dependencies>
        <!-- Source: https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.13.4</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite</artifactId>
            <version>1.13.4</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
````
<br>

## 🎯 Objetivo do Projeto

Além de praticar o CRUD tradicional, o projeto busca exercitar conceitos importantes da programação orientada a objetos, como: Abstração, Herança, Polimorfismo, Interfaces, uso de Generics e estrutura de baixo acoplamento.

----

### Fabio peretti Guimarães | PROJETO 2 Ebac mod 25 | MAI 2026