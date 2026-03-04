package dev.reno.dmr.common.model;

import dev.reno.dmr.common.type.AlgorithmType;
import dev.reno.dmr.common.type.StatusType;

import java.util.UUID;

public record ResponseJobDto(
        UUID id,
        StatusType status,
        AlgorithmType algorithm,
        int[] payload
) {
}