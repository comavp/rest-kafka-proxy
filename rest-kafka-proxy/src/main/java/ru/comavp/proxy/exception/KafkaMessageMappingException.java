package ru.comavp.proxy.exception;

public class KafkaMessageMappingException extends RuntimeException {

    public KafkaMessageMappingException(Exception cause) {
        super(cause);
    }
}
