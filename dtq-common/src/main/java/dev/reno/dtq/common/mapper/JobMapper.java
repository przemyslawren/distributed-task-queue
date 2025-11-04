package dev.reno.dtq.common.mapper;

import dev.reno.dtq.common.model.RequestJobDto;
import dev.reno.dtq.common.model.ResponseJobDto;
import dev.reno.dtq.common.type.StatusType;

import java.util.UUID;

public final class JobMapper {

    private JobMapper() {
        throw new IllegalArgumentException("This is a utility class and cannot be instantiated");
    }

    public static ResponseJobDto toResponse(RequestJobDto job, StatusType status) {
        return new ResponseJobDto(
                UUID.randomUUID(),
                status,
                job.algorithm(),
                job.payload()
        );
    }
}