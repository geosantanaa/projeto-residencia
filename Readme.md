# Projeto E-commerce API

Bem-vindo ao projeto da API de um **E-commerce**. Esta aplicação serve como um backend completo para gerenciar clientes, produtos, endereços e pedidos, oferecendo funcionalidades de CRUD (Create, Read, Update, Delete) para todas as entidades.

---

## 📖 Sobre o Projeto

O projeto foi construído utilizando o framework **Spring Boot** e segue uma arquitetura orientada a serviços. A API permite gerenciar clientes e seus respectivos endereços, cadastrar produtos e processar pedidos, com validações robustas e tratamento de erros para garantir a integridade dos dados.

### Funcionalidades Principais

* **Clientes:** Crie, visualize, altere, liste e exclua clientes, incluindo a validação de CPF.
* **Endereços:** Crie, visualize, altere, liste e exclua endereços, vinculados a clientes.
* **Produtos:** Cadastre novos produtos, visualize, altere, liste e exclua produtos.
* **Pedidos:** Crie, visualize, altere, liste e exclua pedidos.
* **Validação de Dados:** O sistema realiza validações em tempo real para garantir que os dados de entrada estejam corretos, evitando erros de negócio.
* **Tratamento de Erros:** Respostas de erro claras e informativas são fornecidas para facilitar a depuração.

---

## 🚀 Tecnologias Utilizadas

* **Java 17:** Linguagem de programação.
* **Spring Boot 2.x:** Framework para facilitar a criação de aplicações.
* **Spring Data JPA:** Para a persistência de dados.
* **ModelMapper:** Para o mapeamento de objetos DTO (Data Transfer Object) para entidades e vice-versa.
* **Lombok:** Para reduzir a verbosidade do código.
* **Maven:** Gerenciador de dependências e automação de builds.
* **Log4j2:** Para registro de logs da aplicação.
* **Validação Bean Validation (javax.validation):** Para validação de dados de entrada.

---

## ⚙️ Como Rodar a Aplicação

Para rodar o projeto localmente, siga os passos abaixo:

1.  **Pré-requisitos:** Certifique-se de ter o **JDK 17** e o **Maven** instalados na sua máquina.
2.  **Clone o repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd [NOME_DO_SEU_PROJETO]
    ```
3.  **Configurar o Banco de Dados:** A aplicação utiliza um banco de dados relacional. Você precisará configurar as credenciais no arquivo `src/main/resources/application.properties`.
4.  **Compilar e Rodar:**
    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080`.

---

## 💻 Documentação da API

A seguir estão os endpoints disponíveis na API. Todas as requisições devem ser feitas para a URL base `http://localhost:8080/ecommerce`.

### Endpoints de Clientes

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/ecommerce/cliente` | Cria um novo cliente. |
| `GET` | `/ecommerce/cliente/id/{id}` | Busca um cliente pelo ID. |
| `GET` | `/ecommerce/cliente` | Lista todos os clientes. |
| `PUT` | `/ecommerce/cliente/id/{id}` | Altera os dados de um cliente existente. |
| `DELETE`| `/ecommerce/cliente/id/{id}` | Exclui um cliente pelo ID. |

### Endpoints de Produtos

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/ecommerce/produto` | Cria um novo produto. |
| `GET` | `/ecommerce/produto/id/{id}` | Busca um produto pelo ID. |
| `GET` | `/ecommerce/produto` | Lista todos os produtos. |
| `PUT` | `/ecommerce/produto/id/{id}` | Altera os dados de um produto existente. |
| `DELETE`| `/ecommerce/produto/id/{id}` | Exclui um produto pelo ID. |

### Endpoints de Endereços

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/ecommerce/endereco` | Cria um novo endereço. |
| `GET` | `/ecommerce/endereco/id/{id}` | Busca um endereço pelo ID. |
| `GET` | `/ecommerce/endereco` | Lista todos os endereços. |
| `PUT` | `/ecommerce/endereco/id/{id}` | Altera os dados de um endereço existente. |
| `DELETE`| `/ecommerce/endereco/id/{id}` | Exclui um endereço pelo ID. |

### Endpoints de Pedidos

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/ecommerce/pedido` | Cria um novo pedido. |
| `GET` | `/ecommerce/pedido/id/{id}` | Busca um pedido pelo ID. |
| `GET` | `/ecommerce/pedido` | Lista todos os pedidos. |
| `PUT` | `/ecommerce/pedido/id/{id}` | Altera os dados de um pedido existente. |
| `DELETE`| `/ecommerce/pedido/id/{id}` | Exclui um pedido pelo ID. |

### Tratamento de Erros

A API retorna códigos de status HTTP apropriados e objetos JSON com mensagens de erro claras, facilitando a identificação e a correção de problemas.

Exemplo de resposta de erro:

```json
{
  "codigoErro": 1001,
  "mensagem": "Cliente não encontrado.",
  "validacoes": []
}