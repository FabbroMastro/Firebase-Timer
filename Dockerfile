FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY domotica.json .

COPY app.jar /app/app.jar

CMD java -jar /app/app.jar