
# Backend do Projeto CPMatMed

## Descrição

Este é o backend do projeto **CPMatMed** (Controle de Pedido de Material e Medicamento).
O backend foi desenvolvido utilizando Java 17, Spring Boot e Hibernate, 
com um banco de dados PostgreSQL para persistência. 

O projeto segue a arquitetura RESTful, fornecendo endpoints para
gestão de pedidos e produtos.

## Requisitos

Antes de executar o backend, verifique os seguintes pré-requisitos:

- **Java 17** ou superior.
- **Maven** para construção do projeto.
- **PostgreSQL** configurado e em execução pelo docker.

## Configuração do Backend

### Passo 1: Clonar o Repositório
# O PostgresSQL ele busca de um container docker.
  pode verificar no docker-compose.yml dentro da aplicação.

Configure o banco de dados PostgreSQL com a seguinte URL de conexão:
Banco de Dados: cpmatmed
  datasource:
    url: jdbc:postgresql://db:5432/dbpmatmed
    username: postgres
    password: postgres
Para compilar o backend, você pode usar Maven 

Se estiver usando Maven, execute:
mvn clean install

Clonando o repositório para seu ambiente local:

git clone <URL_DO_REPOSITORIO>
cd cpmatmed-backend

# Executando o Backend
Para executar o backend, use o seguinte comando:

mvn spring-boot:run 

ou usando o pacote

java -jar -Dspring.profiles.active=dev target/cpmatmed-backend.jar

API Endpoints
O backend expõe os seguintes endpoints para interação com o frontend:
GET /api/pedidos: Retorna uma lista de todos os pedidos.
GET /api/pedidos/{id}: Retorna os detalhes de um pedido específico, incluindo os produtos.
POST /api/pedidos: Cria um novo pedido.

Exemplo de resposta para GET /api/pedidos:

json
Sempre exibir os detalhes

Copiar
[
  {
    "id": 1,
    "nomeComprador": "João Silva",
    "nomeFornecedor": "Farmácia XYZ",
    "totalProdutos": 10,
    "valorTotal": 250.0
  },
  {
    "id": 2,
    "nomeComprador": "Maria Souza",
    "nomeFornecedor": "Medicamentos ABC",
    "totalProdutos": 5,
    "valorTotal": 120.0
  }
]
# CORS (Cross-Origin Resource Sharing)
O CORS está configurado no backend para aceitar requisições de qualquer 
origem durante o desenvolvimento
 (através da anotação @CrossOrigin(origins = "*")).

Em produção, você deve restringir o CORS para o domínio do frontend. 
Para isso, edite o código da seguinte forma:

@CrossOrigin(origins = "http://localhost:4200")

# Contribuição
Caso deseje contribuir para o projeto, basta seguir as etapas abaixo:

Faça um fork do repositório.
Crie uma nova branch para a funcionalidade ou correção.
Envie um pull request com a descrição da alteração.
