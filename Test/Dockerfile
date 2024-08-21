FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app


COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/Test-0.0.1-SNAPSHOT.war /app/employee-app.war

EXPOSE 8080

CMD ["java", "-jar", "/app/employee-app.war"]
