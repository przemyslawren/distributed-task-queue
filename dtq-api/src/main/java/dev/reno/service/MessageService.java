package dev.reno.service;

import dev.reno.model.ResponseJobDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    void sendMessage(ResponseJobDto responseJobDto) {
        LOG.info("Message sent: {}", responseJobDto.toString());
    }
}