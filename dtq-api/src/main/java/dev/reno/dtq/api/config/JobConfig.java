package dev.reno.dtq.api.config;

import dev.reno.dtq.api.service.JobService;
import dev.reno.dtq.api.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    JobService jobService(MessageService messageService) {
        return new JobService(messageService);
    }
}