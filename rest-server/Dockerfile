FROM openjdk:19-jdk-alpine

ARG SERVER_PORT
ARG JAR_FILE=target/*.jar

ENV SERVER_PORT ${SERVER_PORT}

COPY ${JAR_FILE} rest-server.jar
ENTRYPOINT ["java", "-jar", "/rest-server.jar"]