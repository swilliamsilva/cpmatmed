# ------------------------------
# Estágio de build
# ------------------------------
FROM maven:3.8.6-jdk-8 AS builder

WORKDIR /build

# Copia apenas o necessário
COPY backend/pom.xml ./pom.xml
COPY backend/src ./src

# Executa build do jar
RUN mvn clean package -DskipTests && ls -lh target/

# ------------------------------
# Estágio final
# ------------------------------
FROM openjdk:8-jre-alpine

WORKDIR /app

# Copia o .jar gerado no build
COPY --from=builder /build/target/backend-*.jar ./backend.jar

# Copia o start.sh que está fora da pasta backend
COPY start.sh .

RUN chmod +x start.sh
CMD ["./start.sh"]
