# Build stage
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /workspace/app

# Cache Maven dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Build application with Java 21
COPY src src
RUN mvn package -DskipTests -Djavac.version=21

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy built JAR (Java 21 optimized)
COPY --from=builder /workspace/app/target/*.jar app.jar

# Java 21 specific optimizations
ENV JAVA_OPTS="-XX:+UseZGC -Xmx512m -Xms256m"
EXPOSE 8080

# Run with Java 21 preview features (if needed)
ENTRYPOINT ["java", "-jar", "app.jar"]