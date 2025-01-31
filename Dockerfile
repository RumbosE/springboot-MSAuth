# Etapa de construcción
FROM openjdk:17-jdk-alpine AS builder
WORKDIR /app
COPY . .

# Generar el archivo JAR (si es necesario)
RUN ./gradlew build -x test

# Etapa final (ejecución)
FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE ${APP_PORT}

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]