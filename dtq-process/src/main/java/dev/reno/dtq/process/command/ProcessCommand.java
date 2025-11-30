package dev.reno.dtq.process.command;

import akka.actor.typed.ActorRef;
import dev.reno.dtq.common.model.ResponseJobDto;

public record ProcessCommand(
        ResponseJobDto job,
        ActorRef<Command> sender
) implements Command {
}