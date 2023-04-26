FROM openjdk:17

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/my-spring-boot-app.jar"]