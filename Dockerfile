# Utiliza uma imagem Maven com JDK 8 para o build
FROM maven:3.8.6-jdk-8 AS builder

WORKDIR /build

# Copia apenas os arquivos necessários para o build (otimiza cache do Docker)
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# --- Fase de produção ---
FROM openjdk:8-jre-alpine

WORKDIR /app

# Copia o JAR gerado para o diretório atual

COPY --from=builder /build/target/*.jar ./backend.jar

# Copia o script de inicialização
COPY start.sh .

# Torna o script executável
RUN chmod +x start.sh

# Define o comando de inicialização

CMD ["./start.sh", "--spring.profiles.active=prod"]