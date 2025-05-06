# ------------------------------
# Estágio de construção (build)
# ------------------------------
FROM maven:3.6.3-jdk-8 AS builder

# Diretório de trabalho no estágio de build
WORKDIR /build

# Copia APENAS o backend (dentro de cpmatmed)
COPY cpmatmed/backend/pom.xml .
COPY cpmatmed/backend/src ./src

# Baixa dependências e compila o projeto
RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

# ------------------------------
# Estágio final de execução
# ------------------------------
FROM openjdk:8-jre-alpine

# Diretório de trabalho final (dentro de cpmatmed)
WORKDIR /cpmatmed

# Copia o JAR e o script de inicialização
COPY --from=builder /build/target/backend-*.jar ./backend.jar
COPY cpmatmed/start.sh .

# Permissões e execução
RUN chmod +x start.sh
CMD ["./start.sh"]