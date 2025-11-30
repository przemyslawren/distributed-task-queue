package dev.reno.dtq.api.config;

import dev.reno.dtq.api.service.MessageService;
import dev.reno.dtq.common.model.ResponseJobDto;
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