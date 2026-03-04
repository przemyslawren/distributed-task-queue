package dev.reno.dmr.api.config;

import dev.reno.dmr.api.service.JobService;
import dev.reno.dmr.api.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    JobService jobService(MessageService messageService) {
        return new JobService(messageService);
    }
}