package dev.reno.model;

import dev.reno.type.AlgorithmType;

public record RequestJobDto(
        AlgorithmType algorithm,
        Object payload
) {
}