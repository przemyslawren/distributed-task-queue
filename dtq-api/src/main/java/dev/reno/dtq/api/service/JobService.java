package dev.reno.dtq.api.service;

import dev.reno.dtq.common.mapper.JobMapper;
import dev.reno.dtq.common.model.RequestJobDto;
import dev.reno.dtq.common.model.ResponseJobDto;
import dev.reno.dtq.common.type.StatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class JobService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final MessageService messageService;

    public JobService(MessageService messageService) {
        this.messageService = messageService;
    }

    public ResponseEntity<ResponseJobDto> submitJob(RequestJobDto requestJobDto) {
        ResponseJobDto queuedResponseJobDto = JobMapper.toResponse(requestJobDto, StatusType.QUEUED);

        LOG.info("Job created: {}", queuedResponseJobDto);
        try {
            messageService.sendMessage(queuedResponseJobDto);
            LOG.info("Job submitted: {}", queuedResponseJobDto);
        } catch (RuntimeException e) {
            ResponseJobDto failedResponseJobDto = JobMapper.toResponse(requestJobDto, StatusType.FAILED);
            LOG.error("Failed to send job message", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedResponseJobDto);
        }

        return ResponseEntity.accepted().body(queuedResponseJobDto);
    }
}