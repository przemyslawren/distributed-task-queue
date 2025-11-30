package dev.reno.dtq.process.command;

import dev.reno.dtq.common.model.JobResult;

public record ResultCommand(
        JobResult jobResult
) implements Command {
}