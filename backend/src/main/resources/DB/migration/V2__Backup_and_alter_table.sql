/* Backup manual a ser executado pelo DBA */
CREATE TABLE comprador_backup_202405 AS TABLE comprador;

ALTER TABLE comprador ADD COLUMN novo_campo VARCHAR(100);
-- ALTERAR TABELA PRODUTO EXISTENTE
ALTER TABLE produto ADD COLUMN descricao VARCHAR(255);
