package dev.reno.service;

import dev.reno.model.ResponseJobDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    void sendMessage(ResponseJobDto responseJobDto) {
        kafkaTemplate.send("job", String.valueOf(responseJobDto));
        LOG.info("Message sent: {}", responseJobDto.toString());
    }
}