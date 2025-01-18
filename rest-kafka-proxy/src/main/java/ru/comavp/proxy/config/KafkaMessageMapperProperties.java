package ru.comavp.proxy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka-message-mapper")
public class KafkaMessageMapperProperties {

    private Boolean enable = true;
    private String rulesFileName;

    public String getRulesFileName() {
        return rulesFileName;
    }

    public void setRulesFileName(String rulesFileName) {
        this.rulesFileName = rulesFileName;
    }

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
