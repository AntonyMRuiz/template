FROM maven:3.8.8-openjdk-17 AS build

WORKDIR /template-docker

COPY pom.xml ./

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /template-docker

COPY target/template-0.0.1.jar template-docker.jar

EXPOSE 8080

CMD ["java", "-jar", "template-docker.jar"]