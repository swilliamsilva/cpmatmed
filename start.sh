#!/bin/bash
echo "Aguardando o banco subir..."
sleep 10
cd backend
java -jar target/backend-0.0.1-SNAPSHOT.jar
