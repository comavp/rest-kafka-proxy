FROM openjdk:19-jdk-alpine

ARG BASE_URL
ARG BASE_PORT
ARG ENDPOINT
ARG ENABLE_KAFKA_PROXY
ARG TOPIC_NAME
ARG KAFKA_BOOTSTRAP_SERVERS
ARG ENABLE_KAFKA_MESSAGE_MAPPER
ARG JAR_FILE=target/*.jar

ENV BASE_URL ${BASE_URL}
ENV BASE_PORT ${BASE_PORT}
ENV ENDPOINT ${ENDPOINT}
ENV ENABLE_KAFKA_PROXY ${ENABLE_KAFKA_PROXY}
ENV TOPIC_NAME ${TOPIC_NAME}
ENV KAFKA_BOOTSTRAP_SERVERS ${KAFKA_BOOTSTRAP_SERVERS}
ENV ENABLE_KAFKA_MESSAGE_MAPPER ${ENABLE_KAFKA_MESSAGE_MAPPER}

COPY ${JAR_FILE} rest-kafka-proxy.jar
ENTRYPOINT ["java", "-jar", "/rest-kafka-proxy.jar"]