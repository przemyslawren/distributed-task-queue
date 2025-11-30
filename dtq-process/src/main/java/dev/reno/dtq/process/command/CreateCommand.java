package dev.reno.dtq.process.command;

import dev.reno.dtq.common.model.ResponseJobDto;

public record CreateCommand(
        ResponseJobDto job
) implements Command {
}