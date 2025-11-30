package dev.reno.dtq.common.model;

import java.util.Arrays;

public record JobResult(
        int[] result,
        long processingTime
) {
    @Override
    public String toString() {
        return "JobResult[result=" + Arrays.toString(result) + ", processingTime=" + processingTime + "]";
    }
}