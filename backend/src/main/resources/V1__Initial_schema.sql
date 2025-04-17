-- 4. Migrações com Flyway (produção):
CREATE TABLE comprador (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

-- Demais tabelas...
/* DESENVOLVIMENTO APENAS */
CREATE TABLE IF NOT EXISTS comprador (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

-- Demais tabelas com IF NOT EXISTS...
-- Criação das tabelas principais
CREATE TABLE IF NOT EXISTS comprador (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS fornecedor (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

-- Tabela com relacionamentos
CREATE TABLE IF NOT EXISTS pedido (
    id BIGSERIAL PRIMARY KEY,
    comprador_id BIGINT NOT NULL REFERENCES comprador(id),
    fornecedor_id BIGINT NOT NULL REFERENCES fornecedor(id)
);

-- Tabela com dados monetários
CREATE TABLE IF NOT EXISTS produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    preco_unitario DECIMAL(10,2) NOT NULL CHECK (preco_unitario >= 0),
    pedido_id BIGINT NOT NULL REFERENCES pedido(id)
);