package dev.reno.config;

import dev.reno.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Bean
    public MessageService messageService() {
        return new MessageService();
    }
}