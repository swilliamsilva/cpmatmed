#!/bin/bash
echo "Aguardando PostgreSQL iniciar..."

MAX_ATTEMPTS=30
ATTEMPT=0

until nc -z $PGHOST $PGPORT || [ $ATTEMPT -eq $MAX_ATTEMPTS ]; do
    echo "Tentativa $((ATTEMPT+1))/$MAX_ATTEMPTS..."
    sleep 2
    ATTEMPT=$((ATTEMPT+1))
done

if [ $ATTEMPT -eq $MAX_ATTEMPTS ]; then
    echo "Falha ao conectar ao PostgreSQL após $MAX_ATTEMPTS tentativas. Abortando..."
    exit 1
fi

echo "PostgreSQL pronto! Iniciando a aplicação..."
exec java -jar backend.jar "$@"  # Passa argumentos adicionais (como --spring.profiles.active)