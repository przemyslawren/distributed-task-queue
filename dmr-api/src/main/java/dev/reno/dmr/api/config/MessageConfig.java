package dev.reno.dmr.api.config;

import dev.reno.dmr.api.service.MessageService;
import dev.reno.dmr.common.model.ResponseJobDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class MessageConfig {

    @Bean
    public MessageService messageService(KafkaTemplate<String, ResponseJobDto> kafkaTemplate) {
        return new MessageService(kafkaTemplate);
    }
}