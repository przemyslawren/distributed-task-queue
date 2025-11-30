package dev.reno.dtq.process.listener;

import akka.actor.typed.ActorSystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.reno.dtq.common.model.ResponseJobDto;
import dev.reno.dtq.process.command.Command;
import dev.reno.dtq.process.command.CreateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

    ActorSystem<Command> processSystem;
    private final ObjectMapper objectMapper;

    public MessageListener(ActorSystem<Command> processSystem, ObjectMapper objectMapper) {
        this.processSystem = processSystem;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "job", groupId = "dtq-group")
    public void listen(String message) {
        try {
            ResponseJobDto job = objectMapper.readValue(message, ResponseJobDto.class);
            Command processCommand = new CreateCommand(job);
            processSystem.tell(processCommand);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to deserialize message to ResponseJobDto: {}", message, e);
        }
    }
}