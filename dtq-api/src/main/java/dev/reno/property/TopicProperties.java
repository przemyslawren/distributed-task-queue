package dev.reno.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "topic-job")
public record TopicProperties(
        String name,
        int partitionCount,
        int replicaCount
) {
}