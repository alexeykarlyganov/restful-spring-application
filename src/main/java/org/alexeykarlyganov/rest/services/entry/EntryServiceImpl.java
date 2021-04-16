package org.alexeykarlyganov.rest.services.entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.alexeykarlyganov.rest.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntryServiceImpl implements EntryService {

    private final KafkaTemplate<String, Entry> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public EntryServiceImpl(KafkaTemplate<String, Entry> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(Entry entry) {
        log.info("<= Sending new {}", this.jsonToString(entry));

        Message<Entry> message = MessageBuilder
                    .withPayload(entry)
                    .setHeader(KafkaHeaders.TOPIC, "server.users")
                    .build();
        kafkaTemplate.send(message);
    }

    @Override
    @KafkaListener(topics = {"server.users"}, groupId = "server.broadcast", containerFactory = "singleFactory")
    public void receive(@Payload Entry entry, @Headers MessageHeaders headers) {
        log.info("=> CONSUMED: {}", this.jsonToString(entry));

        headers.keySet().forEach(key -> log.info("{} --- {}", key, headers.get(key)));
    }

    private String jsonToString(Entry entry) {
        try {
            return objectMapper.writeValueAsString(entry);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not write JSON info: " + ex);
        }
    }
}
