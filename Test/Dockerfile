FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Test-0.0.1-SNAPSHOT.war /app/employee-app.war

EXPOSE 8080

CMD ["java", "-jar", "/app/employee-app.war"]