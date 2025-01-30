# Etapa de construcción
FROM openjdk:17-jdk-alpine AS builder
WORKDIR /app
COPY . .

# Generar el archivo JAR (si es necesario)
RUN ./gradlew build -x test

# Etapa final (ejecución)
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Instalar bash
RUN apk add --no-cache bash

# Copiar el archivo JAR y el script wait-for-it.sh
COPY --from=builder /app/build/libs/*.jar app.jar
COPY wait-for-it.sh wait-for-it.sh

# Dar permisos de ejecución al script
RUN chmod +x wait-for-it.sh

# Exponer el puerto de la aplicación
EXPOSE ${APP_PORT}