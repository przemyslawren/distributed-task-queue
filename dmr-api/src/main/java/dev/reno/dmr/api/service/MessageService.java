package dev.reno.dmr.api.service;

import dev.reno.dmr.common.model.ResponseJobDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, ResponseJobDto> kafkaTemplate;

    public MessageService(KafkaTemplate<String, ResponseJobDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    void sendMessage(ResponseJobDto responseJobDto) {
        kafkaTemplate.send("job", responseJobDto);
        LOG.info("Message sent: {}", responseJobDto.toString());
    }
}