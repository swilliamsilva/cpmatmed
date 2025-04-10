# 💊 CPMatMed - Controle de Pedido de Material e Medicamento

Sistema completo para controle de pedidos médicos e seus respectivos produtos. A solução é composta por um **frontend em Angular** e um **backend em Spring Boot**, com comunicação via API REST.

---

## 🗂️ Estrutura do Projeto

```text
workspace-cpmatmed/
├── backend/     # Backend em Java + Spring Boot + PostgreSQL
├── cpmatmed-frontend/    # Frontend em Angular 18
└── README.md    # Este arquivo

Objetivo
Oferecer uma aplicação simples e eficiente para:

Listagem de pedidos médicos

Visualização dos produtos associados a um pedido

Registro de novos pedidos

⚙️ Tecnologias Utilizadas
Backend
Java 17, Spring Boot
Spring Data JPA / Hibernate
PostgreSQL
JUnit para testes
Docker
Maven

Arquivo application.yml para configuração

Frontend
Angular 18

Bootstrap 5
jQuery (para interações específicas)
HTML/CSS
Testes com Jasmine + Karma

🚀 Como Executar o Projeto
1. Clone o repositório


git clone https://github.com/seu-usuario/cpmatmed.git
cd workspace-cpmatmed
2. Configure o Backend
Acesse a pasta:

cd backend
➤ Configure o application.yml com seu banco PostgreSQL.
➤ Execute com:

mvn clean install
mvn spring-boot:run
A API estará disponível em: http://localhost:8080/api/pedidos

3. Configure o Frontend
Abra uma nova aba e acesse a pasta:

cd frontend
➤ Instale as dependências:
npm install
➤ Rode a aplicação:
ng serve
A aplicação estará disponível em: http://localhost:4200

🧪 Testes
cd backend

mvn test

Frontend

cd frontend
ng test
📌 Observações
O CORS está liberado para desenvolvimento no backend com @CrossOrigin(origins = "*")

Em produção, recomendamos configurar os domínios específicos no CORS.

O frontend acessa a API via http://localhost:8080/api/pedidos.

