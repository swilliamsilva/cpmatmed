Backend do Projeto CPMatMed

Controle de Pedido de Material e Medicamento
📝 Descrição

Este é o backend do sistema CPMatMed, responsável pela gestão de pedidos de materiais e medicamentos.
O sistema foi desenvolvido em Java 8 com Spring Boot e Hibernate, utilizando PostgreSQL como banco de dados.

A aplicação segue a arquitetura RESTful, oferecendo endpoints para operações com pedidos e produtos.
⚙️ Requisitos

    Java 8

    Maven 3+

    PostgreSQL instalado localmente e configurado

    Sistema Operacional: Windows 7

💠 Configuração do Banco de Dados

Configure o PostgreSQL local com os seguintes dados:

    Banco: cpmatmed

    URL de conexão: jdbc:postgresql://localhost:5432/cpmatmed

    Usuário: postgres

    Senha: postgres

    ⚠️ Certifique-se de que o banco está criado e acessível antes de executar a aplicação.

🚀 Como Executar
Clonando o repositório

git clone <URL_DO_REPOSITORIO>
cd workspace-cpmatmed/cpmatmed/backend

Compilando o projeto

mvn clean install

Executando a aplicação

# Perfil de desenvolvimento
mvn spring-boot:run -Dspring-boot.run.profiles=dev

ou

java -jar -Dspring.profiles.active=dev target/backend-0.0.1-SNAPSHOT.jar

🌐 Endpoints da API
Pedidos
Método	Endpoint	Descrição
GET	/api/pedidos	Lista todos os pedidos
GET	/api/pedidos/{id}	Detalha um pedido e seus produtos
POST	/api/pedidos	Cria um novo pedido
Exemplo de resposta (GET /api/pedidos):

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

🌍 CORS

Durante o desenvolvimento, o backend está configurado para aceitar requisições de qualquer origem:

@CrossOrigin(origins = "*")

Para produção, restrinja o acesso conforme necessário, por exemplo:

@CrossOrigin(origins = "http://localhost:4200")

👥 Contribuição

    Faça um fork do repositório

    Crie uma branch para sua feature ou correção

    Envie um Pull Request com a descrição da sua contribuição

🥪 Perfis de Execução
Ambiente	Banco de Dados	DDL Generation	CORS	Dados Iniciais
Desenvolvimento	PostgreSQL Local	update	Aberto (*)	schema.sql + data.sql
Testes	H2 (Memória)	create-drop	Fechado (Mockado)	Automático (via Testes)
Produção	PostgreSQL Real	validate	Restrito (Configurado)	Flyway ou Liquibase
📦 Comandos Rápidos

# Desenvolvimento
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Testes
mvn test -Dspring.profiles.active=test

# Produção
java -jar target/backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

📤 Fluxo de Deploy

    Desenvolvimento:

        Branch: development

        Banco local

        Scripts automáticos (data.sql)

    Testes:

        Branch: test

        Banco H2 em memória

        Testes de integração automatizados

    Produção:

        Branch: production

        Banco real

        Migrações controladas via Flyway ou Liquibase

🔐 Segurança e Boas Práticas

    ⚠️ Nunca deixar senhas hardcoded no código

    Utilize variáveis de ambiente (DB_PASSWORD, etc.)

    Implemente HTTPS no ambiente de produção

    Faça backups regulares de bancos de dados em produção

    Revise as configurações de CORS antes do deploy final