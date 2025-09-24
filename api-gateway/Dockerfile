FROM gradle:8.9.0-jdk21 AS build
WORKDIR /workspace
COPY . .
RUN gradle :api-gateway:bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/api-gateway/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["sh","-c","java -jar /app/app.jar"]
