FROM openjdk:17-jdk-slim


RUN apt-get update && apt-get install -y maven

ENV M2_HOME /usr/local/maven
ENV M2_PATH $M2_HOME/bin
ENV PATH $PATH:$M2_PATH

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Run Maven to build the application
RUN mvn package

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file when the container launches
CMD ["java", "-jar", "target/app-0.0.1-SNAPSHOT.jar"]