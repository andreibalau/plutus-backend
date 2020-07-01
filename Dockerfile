FROM openjdk:11-jre-slim
LABEL vendor="Catalin Matache"
MAINTAINER Catalin Matache "https://i-catalin.ro"
COPY target/Plutus-0.0.1-SNAPSHOT.jar plutus-latest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "plutus-latest.jar"]
