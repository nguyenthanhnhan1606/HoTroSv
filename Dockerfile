FROM maven:3.9.4-openjdk:19 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19
LABEL authors="nhannt166"
COPY --from = build /target/Wedhotrots-0.0.1-SNAPSHOT.jar Wedhotrots-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "Wedhotrots-0.0.1-SNAPSHOT.jar"]
