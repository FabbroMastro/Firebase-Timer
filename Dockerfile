FROM openjdk:17-jdk-slim

WORKDIR /Firebase-Timer

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app-0.0.1-SNAPSHOT.jar"]