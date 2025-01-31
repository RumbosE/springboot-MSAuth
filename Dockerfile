
# Etapa de desarrollo
FROM openjdk:17-jdk-alpine AS dev
WORKDIR /app
COPY . .
CMD ["./gradlew", "bootRun"]
# Etapa de construcción

FROM openjdk:17-jdk-alpine AS deps
WORKDIR /app

# Copiar solo el archivo de definición de dependencias
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Descargar dependencias
RUN ./gradlew dependencies

# Etapa de construcción
FROM openjdk:17-jdk-alpine AS build
WORKDIR /app

# Copiar solo los archivos necesarios
COPY --from=deps /app/build.gradle /app/settings.gradle /app/
COPY --from=deps /app/gradle /app/gradle

# Copiar el resto de los archivos
COPY . .

# Construir la aplicación
RUN ./gradlew build -x test

# Etapa de empaquetado
FROM openjdk:17-jdk-alpine AS packager
WORKDIR /app

# Copiar el archivo JAR
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]

# Exponer el puerto 8080
EXPOSE $PORT
