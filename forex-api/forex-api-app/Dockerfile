FROM gradle:8.9.0-jdk21 AS build
WORKDIR /workspace
COPY . .
RUN gradle :forex-api:forex-api-app:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/forex-api/forex-api-app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar /app/app.jar"]
