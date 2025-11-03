package dev.reno.dtq.api.model;

import dev.reno.dtq.api.type.AlgorithmType;
import dev.reno.dtq.api.type.StatusType;

import java.util.UUID;

public record ResponseJobDto(
        UUID id,
        StatusType status,
        AlgorithmType algorithm,
        Object payload
) {
}