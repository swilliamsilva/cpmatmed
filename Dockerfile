# ------------------------------
# Estágio de construção (build)
# ------------------------------
FROM maven:3.8.6-jdk-8 AS builder

WORKDIR /build

# Copia o código-fonte e o pom.xml
COPY backend/pom.xml .
COPY backend/src ./src

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests && ls -l /build/target/  # Verificação aqui

# ------------------------------
# Estágio final de execução
# ------------------------------
FROM openjdk:8-jre-alpine

WORKDIR /app

# Copia o JAR gerado (caminho correto!)
COPY --from=builder /build/target/backend-*.jar ./backend.jar

# Copia o script de inicialização
COPY start.sh .

# Permissões e execução
RUN chmod +x start.sh
CMD ["./start.sh"]