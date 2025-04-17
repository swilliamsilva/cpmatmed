/* Backup manual a ser executado pelo DBA */
CREATE TABLE comprador_backup_202405 AS TABLE comprador;

ALTER TABLE comprador ADD COLUMN novo_campo VARCHAR(100);