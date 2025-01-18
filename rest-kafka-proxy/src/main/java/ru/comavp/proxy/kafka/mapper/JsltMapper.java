package ru.comavp.proxy.kafka.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.comavp.proxy.config.KafkaMessageMapperProperties;
import ru.comavp.proxy.exception.KafkaMessageMappingException;

import java.io.File;

@Component
@ConditionalOnProperty(prefix = "kafka-message-mapper", name = "enable", havingValue = "true")
public class JsltMapper implements KafkaMessageMapper {

    private ObjectMapper objectMapper;
    private Expression jsltExpression;

    @Autowired
    public JsltMapper(KafkaMessageMapperProperties properties, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        jsltExpression = Parser.compile(new File("resource:" + properties.getRulesFileName()));
    }

    @Override
    public String map(String string) {
        try {
            return objectMapper.writeValueAsString(jsltExpression.apply(objectMapper.readTree(string)));
        } catch (Exception e) {
            throw new KafkaMessageMappingException(e);
        }
    }
}
