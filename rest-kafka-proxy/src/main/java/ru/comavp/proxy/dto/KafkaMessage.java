package ru.comavp.proxy.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class KafkaMessage {

    private UUID messageId;
    private LocalDateTime redirectTime;
    private String data;

    public KafkaMessage(String data) {
        this.messageId = UUID.randomUUID();
        this.redirectTime = LocalDateTime.now();
        this.data = data;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getRedirectTime() {
        return redirectTime;
    }

    public void setRedirectTime(LocalDateTime redirectTime) {
        this.redirectTime = redirectTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
