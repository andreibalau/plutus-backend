FROM adoptopenjdk/openjdk11:alpine-slim
FROM adoptopenjdk/openjdk11:armv7l-ubuntu-jre-11.0.8_10
LABEL vendor="Catalin Matache"
MAINTAINER Catalin Matache "https://i-catalin.ro"
COPY target/Plutus-0.0.1-SNAPSHOT.jar plutus-latest.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "plutus-latest.jar"]
