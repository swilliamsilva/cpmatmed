# ------------------------------
# Estágio de construção (build)
# ------------------------------
FROM maven:3.6.3-jdk-8 AS builder

WORKDIR /build

# Copia o pom.xml e código-fonte
COPY backend/pom.xml .
COPY backend/src ./src

# Compila o projeto
RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

# ------------------------------
# Estágio final de execução
# ------------------------------
FROM openjdk:8-jre-alpine

WORKDIR /app

# Copia o JAR e o script
COPY --from=builder /build/target/backend-*.jar ./backend.jar
COPY start.sh .

# Permissões e execução
RUN chmod +x start.sh
CMD ["./start.sh"]