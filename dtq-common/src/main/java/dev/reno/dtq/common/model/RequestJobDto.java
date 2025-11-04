package dev.reno.dtq.common.model;

import dev.reno.dtq.common.type.AlgorithmType;

public record RequestJobDto(
        AlgorithmType algorithm,
        Object payload
) {
}