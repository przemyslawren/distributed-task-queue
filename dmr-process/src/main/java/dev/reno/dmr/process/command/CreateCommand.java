package dev.reno.dmr.process.command;

import dev.reno.dmr.common.model.ResponseJobDto;

public record CreateCommand(
        ResponseJobDto job
) implements Command {
}