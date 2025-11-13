# For minimal effort during deployments
# Example commands to be ran:
# docker build --no-cache -t renowave .
# docker run -d -p 9090:8080 renowave

# Stage 1: Build the JAR
FROM gradle:8.10.2-jdk21 AS builder
WORKDIR /app

# Copy the Gradle files and source code
COPY . .

# Build the project (produces build/libs/*.jar)
RUN gradle clean build --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar renowave.jar

# Run the app
ENTRYPOINT ["java", "-jar", "/app/renowave.jar"]
