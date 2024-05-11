# Stage 1: Build stage
FROM maven:3.9.4-openjdk-19 AS build
WORKDIR /app
COPY . .
RUN mvn clean

# Stage 2: Production stage
FROM openjdk:19
LABEL authors="nhannt166"
WORKDIR /app
COPY --from=build /target/Wedhotrots-0.0.1-SNAPSHOT.jar Wedhotrots-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "Wedhotrots-0.0.1-SNAPSHOT.jar"]
