package dev.reno.dtq.common.config;

import dev.reno.dtq.common.model.ResponseJobDto;
import dev.reno.dtq.common.property.KafkaConfigProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({KafkaConfigProperties.class})
public class MqConfig {

    @Bean
    public ConsumerFactory<String, ResponseJobDto> consumerFactory(KafkaConfigProperties kafkaConfigProperties) {
        final Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.bootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfigProperties.autoOffsetReset());

        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ResponseJobDto> kafkaListenerContainerFactory(
            ConsumerFactory<String, ResponseJobDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ResponseJobDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(10);

        return factory;
    }

    @Bean
    public ProducerFactory<String, ResponseJobDto> producerFactory(KafkaConfigProperties kafkaConfigProperties) {
        final Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.bootstrapServers());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, ResponseJobDto> kafkaTemplate(ProducerFactory<String, ResponseJobDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}