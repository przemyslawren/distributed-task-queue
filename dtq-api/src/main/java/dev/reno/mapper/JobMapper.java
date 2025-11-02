package dev.reno.mapper;

import dev.reno.model.RequestJobDto;
import dev.reno.model.ResponseJobDto;
import dev.reno.type.StatusType;

import java.util.UUID;

public class JobMapper {

    public JobMapper() {
        throw new IllegalArgumentException("This is a utility class and cannot be instantiated");
    }

    public static ResponseJobDto toQueuedResponseJobDto(RequestJobDto job) {
        return new ResponseJobDto(
                UUID.randomUUID(),
                StatusType.QUEUED,
                job.algorithm(),
                job.payload()
        );
    }

    public static ResponseJobDto toFailedResponseJobDto(RequestJobDto job) {
        return new ResponseJobDto(
                UUID.randomUUID(),
                StatusType.FAILED,
                job.algorithm(),
                job.payload()
        );
    }
}