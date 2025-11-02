package dev.reno.config;

import dev.reno.service.JobService;
import dev.reno.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    JobService jobService(MessageService messageService) {
        return new JobService(messageService);
    }
}