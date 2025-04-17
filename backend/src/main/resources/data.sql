-- Compradores
INSERT INTO comprador (nome) VALUES ('Hospital Central');
INSERT INTO comprador (nome) VALUES ('Clínica Saúde Total');

-- Fornecedores
INSERT INTO fornecedor (nome) VALUES ('Farma Distribuidora');
INSERT INTO fornecedor (nome) VALUES ('MedEquip Ltda');

-- Pedidos
INSERT INTO pedido (comprador_id, fornecedor_id) VALUES (1, 1);
INSERT INTO pedido (comprador_id, fornecedor_id) VALUES (2, 2);

-- Produtos
INSERT INTO produto (nome, quantidade, preco_unitario, pedido_id) VALUES ('Paracetamol 500mg', 100, 2.50, 1);
INSERT INTO produto (nome, quantidade, preco_unitario, pedido_id) VALUES ('Máscara Cirúrgica', 500, 0.75, 1);
INSERT INTO produto (nome, quantidade, preco_unitario, pedido_id) VALUES ('Termômetro Digital', 50, 25.00, 2);