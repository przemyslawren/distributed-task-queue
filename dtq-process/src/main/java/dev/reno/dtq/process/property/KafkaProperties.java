package dev.reno.dtq.process.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
public record KafkaProperties(
        String bootstrapServers,
        String groupId,
        String autoOffsetReset
) {
}