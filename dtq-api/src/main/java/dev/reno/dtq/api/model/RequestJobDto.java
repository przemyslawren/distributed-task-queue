package dev.reno.dtq.api.model;

import dev.reno.dtq.api.type.AlgorithmType;

public record RequestJobDto(
        AlgorithmType algorithm,
        Object payload
) {
}