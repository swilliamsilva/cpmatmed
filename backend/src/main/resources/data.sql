-- Compradores
INSERT INTO comprador (nome) VALUES ('Hospital Central');
INSERT INTO comprador (nome) VALUES ('Clínica Saúde Total');

-- Fornecedores
INSERT INTO fornecedor (nome) VALUES ('Farma Distribuidora');
INSERT INTO fornecedor (nome) VALUES ('MedEquip Ltda');

-- Produtos
INSERT INTO produto (nome, descricao, preco_unitario)
VALUES ('Paracetamol 500mg', 'Analgésico e antitérmico', 2.50);

INSERT INTO produto (nome, descricao, preco_unitario)
VALUES ('Máscara Cirúrgica', 'Máscara descartável de proteção', 0.75);

INSERT INTO produto (nome, descricao, preco_unitario)
VALUES ('Termômetro Digital', 'Leitura de temperatura corporal', 25.00);

-- Pedidos
INSERT INTO pedido (comprador_id, fornecedor_id) VALUES (1, 1);
INSERT INTO pedido (comprador_id, fornecedor_id) VALUES (2, 2);

-- Itens de pedido
INSERT INTO item_pedido (pedido_id, produto_id, quantidade) VALUES (1, 1, 100);
INSERT INTO item_pedido (pedido_id, produto_id, quantidade) VALUES (1, 2, 500);
INSERT INTO item_pedido (pedido_id, produto_id, quantidade) VALUES (2, 3, 50);
