FROM openjdk:8
WORKDIR /usr/plutus
COPY ./target/Plutus-0.0.1-SNAPSHOT.jar /plutus-latest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/plutus-latest.jar"]