
# 🏥 CPMatMed - Controle de Pedido de Material e Medicamento

Sistema completo para gerenciamento de pedidos médicos e seus respectivos 
produtos, com interface web moderna e backend robusto em Java.

## 🎯 Objetivo
O projeto tem como foco facilitar o controle de pedidos médicos, exibindo informações como:

- Comprador
- Fornecedor
- Lista de produtos adquiridos
- Quantidade e valor total por pedido

## ⚙️ Tecnologias Utilizadas
### 🔧 Backend
- Java 17
- Spring Boot - 
- Spring Data JPA (Hibernate)
- PostgreSQL
- JUnit e MockMvc (testes)
- Maven
- `application.yml` para configuração
- CORS habilitado para desenvolvimento

### 🎨 Frontend
- Angular 18
- Bootstrap 5
- jQuery (operações DOM e AJAX pontuais)
- Angular CLI
- Testes com Karma + Jasmine
- Componentes customizados


## 🧪 Requisitos
- Java 17
- Node.js + npm
- Angular CLI
- PostgreSQL
- Maven
- Docker
- Git

---

## 🚀 Como Executar
### Backend
1. Configure o banco PostgreSQL com a base `cpmatmed`.

2. Ajuste o arquivo `application.yml` em `src/main/resources`:

yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/cpmatmed
    username: seu_usuario
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
server:
  port: 8080

# Frontend
## Vá até a pasta do frontend:

cd cpmatmed-frontend
Instale as dependências:

npm install

Inicie a aplicação:

ng serve

Acesse: http://localhost:4200

Confirme que a URL da API está correta em pedido.service.ts:

private apiUrl = 'http://localhost:8080/api/pedidos';
✅ Funcionalidades
✅ Listagem de pedidos médicos

✅ Visualização de produtos ao clicar no pedido

🚧 Cadastro de novos pedidos (em progresso)

✅ Integração com API REST

✅ Separação em componentes Angular

✅ Testes unitários e integração no backend

🚧 Testes frontend em construção

