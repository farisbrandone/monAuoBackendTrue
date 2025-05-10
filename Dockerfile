# Build stage
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /workspace/app

# Cache Maven dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Build application
COPY src src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy built application
COPY --from=builder /workspace/app/target/*.jar app.jar

# Set Java 21 specific options
ENV JAVA_OPTS="-XX:+UseZGC -Xmx512m -Xms256m"

# Run with new Java 21 features
ENTRYPOINT ["java", "-XX:+EnableDynamicAgentLoading", "-jar", "app.jar"]