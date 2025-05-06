#!/bin/bash
echo "Aguardando PostgreSQL iniciar..."

# Configurações padrão do Railway
PGHOST=${PGHOST:-postgres}  # Usa o nome do serviço PostgreSQL do Railway
PGPORT=${PGPORT:-5432}

# Espera até que o PostgreSQL esteja disponível
until nc -z $PGHOST $PGPORT; do
  echo "PostgreSQL não disponível. Aguardando..."
  sleep 2
done

echo "PostgreSQL pronto! Iniciando aplicação..."
exec java -jar backend.jar --spring.profiles.active=prod