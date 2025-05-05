# Estagio de construcao (build)
FROM maven:3.6.3-jdk-8 AS builder

# Copia o codigo do backend para a pasta /app/backend
COPY backend /cpmatmed/backend

# Compila o projeto Maven
WORKDIR /cpmatmed/backend
RUN mvn clean package -DskipTests

# --------------------------------------------

# Estagio de execucao (imagem final leve)
FROM openjdk:8-jre-alpine

# Diretorio de trabalho final
WORKDIR /cpmatmed

# Copia o JAR gerado no estagio anterior
COPY --from=builder /cpmatmed/backend/target/backend-0.0.1-SNAPSHOT.jar ./backend.jar

# Copia o script de inicializacao
COPY start.sh ./start.sh

# Torna o script executavel
RUN chmod +x start.sh

# Comando para iniciar a aplicacao
CMD ["./start.sh"]