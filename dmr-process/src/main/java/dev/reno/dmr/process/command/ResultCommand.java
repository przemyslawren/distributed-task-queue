package dev.reno.dmr.process.command;

import dev.reno.dmr.common.model.JobResult;

public record ResultCommand(
        JobResult jobResult
) implements Command {
}