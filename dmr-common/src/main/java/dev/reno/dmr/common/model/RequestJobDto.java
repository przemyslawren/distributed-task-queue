package dev.reno.dmr.common.model;

import dev.reno.dmr.common.type.AlgorithmType;

public record RequestJobDto(
        AlgorithmType algorithm,
        int[] payload
) {
}