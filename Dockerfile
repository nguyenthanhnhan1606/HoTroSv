FROM openjdk:19
LABEL authors="nhannt166"
COPY target/Wedhotrots-0.0.1-SNAPSHOT.jar Wedhotrots-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Wedhotrots-0.0.1-SNAPSHOT.jar"]
