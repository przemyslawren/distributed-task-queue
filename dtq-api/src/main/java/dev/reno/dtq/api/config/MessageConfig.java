package dev.reno.dtq.api.config;

import dev.reno.dtq.api.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class MessageConfig {

    @Bean
    public MessageService messageService(KafkaTemplate<String, String> kafkaTemplate) {
        return new MessageService(kafkaTemplate);
    }
}