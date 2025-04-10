-- CriaçAo de tabelas

CREATE TABLE IF NOT EXISTS comprador (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido (
    id SERIAL PRIMARY KEY,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    comprador_id INT NOT NULL REFERENCES comprador(id) ON DELETE CASCADE,
    fornecedor_id INT NOT NULL REFERENCES fornecedor(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL CHECK (quantidade > 0),
    valor_total DECIMAL(10, 2) CHECK (valor_total >= 0),
    pedido_id INT NOT NULL REFERENCES pedido(id) ON DELETE CASCADE
);

-- Dados iniciais

-- Compradores
INSERT INTO comprador (nome) VALUES 
    ('Hospital Sao Joao'), 
    ('Clinica Vida+');

-- Fornecedores
INSERT INTO fornecedor (nome) VALUES 
    ('Fornecedora Medica LTDA'), 
    ('Distribuidora Hospitalar XYZ');

-- Pedidos
INSERT INTO pedido (data, comprador_id, fornecedor_id) VALUES 
    (NOW(), 1, 1),
    (NOW(), 2, 2);

-- Produtos
INSERT INTO produto (nome, quantidade, valor_total, pedido_id) VALUES
    ('Gaze', 100, 50.00, 1),
    ('Algodão', 200, 30.00, 1),
    ('Esparadrapo', 50, 25.00, 1),
    ('Soro', 30, 120.00, 2),
    ('Luvas', 500, 150.00, 2);
