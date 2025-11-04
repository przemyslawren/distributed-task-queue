package dev.reno.dtq.common.model;

import dev.reno.dtq.common.type.AlgorithmType;
import dev.reno.dtq.common.type.StatusType;

import java.util.UUID;

public record ResponseJobDto(
        UUID id,
        StatusType status,
        AlgorithmType algorithm,
        Object payload
) {
}