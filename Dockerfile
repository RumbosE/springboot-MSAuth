FROM eclipse-temurin:21-jdk as builder
LABEL authors="rumbose"

WORKDIR /app

COPY . .

RUN .gradle clean build -DskipTests

# Etapa final (ejecución)

FROM eclipse-temurin:21-jre

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]