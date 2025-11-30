package dev.reno.dtq.process.behavior;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import dev.reno.dtq.common.type.AlgorithmType;
import dev.reno.dtq.process.command.Command;
import dev.reno.dtq.process.command.CreateCommand;
import dev.reno.dtq.process.command.ProcessCommand;
import dev.reno.dtq.process.command.ResultCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ManagerBehavior extends AbstractBehavior<Command> {
    private static final Logger LOG = LoggerFactory.getLogger(ManagerBehavior.class);


    public ManagerBehavior(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> create() {
        return Behaviors.setup(ManagerBehavior::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(CreateCommand.class, command -> {
                    ActorRef<Command> worker = getContext().spawn(WorkerBehavior.create(), createWorkerName(command));
                    worker.tell(new ProcessCommand(command.job(), getContext().getSelf()));
                    return this;
                })
                .onMessage(ResultCommand.class, command -> {
                    LOG.info("Result received: {}", command.jobResult());
                    return this;
                })
                .onAnyMessage(command -> {
                    LOG.info("Wrong command received in manager: {}", command);
                    return this;
                })
                .build();
    }

    private static String createWorkerName(CreateCommand command) {
        UUID uuid = UUID.randomUUID();
        AlgorithmType algorithmUsed = command.job().algorithm();
        return "worker_" + algorithmUsed.name() + "_" + uuid;
    }
}