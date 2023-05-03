FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

RUN mvn dependency:go-offline

# Run Maven to build the application
RUN mvn package

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define environment variable
ENV NAME World

# Run the jar file when the container launches
CMD ["java", "-jar", "target/app-0.0.1-SNAPSHOT.jar"]