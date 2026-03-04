package dev.reno.dtq.process.command;

import org.apache.pekko.actor.typed.ActorRef;
import dev.reno.dtq.common.model.ResponseJobDto;

public record ProcessCommand(
        ResponseJobDto job,
        ActorRef<Command> sender
) implements Command {
}