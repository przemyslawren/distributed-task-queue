package dev.reno.dmr.process.command;

import org.apache.pekko.actor.typed.ActorRef;
import dev.reno.dmr.common.model.ResponseJobDto;

public record ProcessCommand(
        ResponseJobDto job,
        ActorRef<Command> sender
) implements Command {
}