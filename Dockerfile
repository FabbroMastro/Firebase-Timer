FROM openjdk:17

COPY target/app-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/my-spring-boot-app.jar"]