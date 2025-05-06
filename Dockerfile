# ------------------------------
# Estágio de construção (build)
# ------------------------------
FROM maven:3.6.3-jdk-8 AS builder

# Define o diretório de trabalho
WORKDIR /cpmatmed/backend

# Copia o código-fonte do backend
COPY backend /cpmatmed/backend

# Compila o projeto Maven (sem testes)
RUN mvn clean package -DskipTests

# ------------------------------
# Estágio final de execução
# ------------------------------
FROM openjdk:8-jre-alpine

# Define o diretório de trabalho
WORKDIR /cpmatmed/backend

# Copia o JAR gerado no build
COPY --from=builder /cpmatmed/backend/target/backend-0.0.1-SNAPSHOT.jar backend.jar

# Copia o script de inicialização
COPY start.sh start.sh

# Permissão de execução para o script
RUN chmod +x start.sh

# Comando de entrada
CMD ["./start.sh"]
