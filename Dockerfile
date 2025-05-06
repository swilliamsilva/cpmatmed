# Etapa de construção com Maven
FROM maven:3.8.6-jdk-8 AS builder

WORKDIR /build

COPY backend/pom.xml ./pom.xml
COPY backend/src ./src

RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:8-jre-alpine

WORKDIR /app

# Copia o JAR da etapa de build
RUN mkdir -p backend/target
COPY --from=builder /build/target/*.jar backend/target/backend.jar

RUN ls -lh /app


COPY start.sh .
RUN chmod +x start.sh

CMD ["./start.sh"]
