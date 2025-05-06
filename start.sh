#!/bin/bash
echo "Aguardando o PostgreSQL iniciar..."

# Espera até o PostgreSQL estar disponível
until nc -z $PGHOST $PGPORT; do
  echo "PostgreSQL não está disponível. Aguardando..."
  sleep 2
done

echo "PostgreSQL está pronto! Iniciando a aplicação..."

# Loop para reiniciar em caso de crash
while true; do
  java -jar backend.jar
  echo "Aplicação crashou. Reiniciando em 5 segundos..."
  sleep 5
done