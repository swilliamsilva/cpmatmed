# 💊 CPMatMed - Controle de Pedido de Material e Medicamento

Sistema completo para controle de pedidos médicos e seus respectivos produtos. A solução é composta por um **frontend em Angular** e um **backend em Spring Boot**, com comunicação via API REST.

## 🧩 Estrutura do Projeto

O projeto está dividido em duas aplicações independentes:
- **Frontend (Angular 10)**: Interface gráfica do sistema.
- **Backend (Java 8 + Spring Boot)**: API REST com lógica de negócios e persistência de dados.

---

## 🚀 Tecnologias Utilizadas

### Frontend
- Angular 10
- TypeScript
- RxJS
- Jasmine & Karma (testes)

### Backend
- Java 8
- Spring Boot
- Spring Web
- Spring Data JPA
- Banco H2 (ou outro)
- JUnit & Mockito (testes unitários)

---
## 🛠️ Funcionalidades Principais

- CRUD de **Fornecedores**, **Produtos**, **Compradores** e **Pedidos**.
- Comunicação entre frontend e backend via HTTP (API REST).
- Testes unitários para componentes e serviços.
- Estrutura modular com lazy loading no Angular.
- Organização em DTOs, Mappers e Controllers no backend.

---

## ▶️ Como Executar o Projeto

### Backend

## ⚙️ Requisitos

- Java 8+
- Maven 3+
- PostgreSQL (em execução local ou via Docker)
- Sistema operacional: Windows 7 ou compatível
## ▶️ Como Executar

### Backend (Java)
1. Abra o projeto em uma IDE como Eclipse ou VS Code.
2. Compile com Java 8.
3. Rode a aplicação (`BackendApplication.java`), pela IDE.
4. A API será exposta em: `http://localhost:8080`.
5. Para executar

# Para rodar os teste:

mvn clean install -Dspring.profiles.active=test

# Para executar como desenvolvimento:
mvn spring-boot:run -Dspring-boot.run.profiles=dev
mvn test -Dspring.profiles.active=test
 Ou com JAR:
java -jar -Dspring.profiles.active=dev target/backend-0.0.1-SNAPSHOT.jar

mvn spring-boot:run -Dspring-boot.run.profiles=prod

Na Web pode executar usando railways em ambiente de produção.


https://cpmatmed-backend-production.up.railway.app/

https://cpmatmed-backend-production.up.railway.app/actuator

https://cpmatmed-backend-production.up.railway.app/api/pedidos
  
pelo postman
GET https://cpmatmed-backend-production.up.railway.app/api/pedidos




### Frontend (Angular)
1. Acesse a pasta do frontend.
2. Instale as dependências:

```bash
npm install

3. Execute o aplicativo.
ng serve 
ou
ng test
---

## 💾 Configuração do Banco de Dados

Certifique-se de que o banco de dados `cpmatmed` esteja criado e acessível.
Exemplo de conexão (`application-dev.properties`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cpmatmed
spring.datasource.username=postgres
spring.datasource.password=postgres
mvn clean install

### 1. Clonar o Repositório

```
bash
git clone https://github.com/swilliamsilva/cpmatmed.git
cd cpmatmed

# CORS - Frontend
Durante o desenvolvimento, o CORS está aberto para qualquer origem:
@CrossOrigin(origins = "*")

Para produção, configure especificamente o domínio:
@CrossOrigin(origins = "http://localhost:4200")

# Exemplo de Resposta: POST /api/...

Primeiro faça o POST de um comprador
https://cpmatmed-backend-production.up.railway.app/api/compradores
{
  "nome": "Dra. Ana Clara"
}

Depois faça  POST de um fornecedor
https://cpmatmed-backend-production.up.railway.app/api/fornecedores
{
  "nome": "Clínica São Rafael"
}

E um POST do produto
https://cpmatmed-backend-production.up.railway.app/api/produtos

{
  "id": null,
  "nome": "Álcool 70%",
  "descricao": "Frasco de álcool 70% com 500ml",
  "quantidade": 10,
  "precoUnitario": 4.50,
  "valorTotal": 45.00,
  "fornecedorId": 1
}

Para um pedido por vez.
https://cpmatmed-backend-production.up.railway.app/api/pedidos
{
  "compradorId": 1,
  "fornecedorId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    },
    {
      "produtoId": 2,
      "quantidade": 3
    }
  ]
}



