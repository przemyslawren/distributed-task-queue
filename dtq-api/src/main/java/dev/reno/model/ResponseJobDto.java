package dev.reno.model;

import dev.reno.type.AlgorithmType;
import dev.reno.type.StatusType;

import java.util.UUID;

public record ResponseJobDto(
        UUID id,
        StatusType status,
        AlgorithmType algorithm,
        Object payload
) {
}