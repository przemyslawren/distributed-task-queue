package dev.reno.dmr.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
public record KafkaConfigProperties(
        String bootstrapServers,
        String autoOffsetReset
) {
}