package dev.reno.dtq.process.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

    @KafkaListener(topics = "job", groupId = "dtq-group")
    public void listen(String message) {
        LOG.info(message);
    }
}