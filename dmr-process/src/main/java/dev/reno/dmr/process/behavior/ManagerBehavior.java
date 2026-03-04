package dev.reno.dmr.process.behavior;

import org.apache.pekko.actor.typed.ActorRef;
import org.apache.pekko.actor.typed.Behavior;
import org.apache.pekko.actor.typed.javadsl.AbstractBehavior;
import org.apache.pekko.actor.typed.javadsl.ActorContext;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.actor.typed.javadsl.Receive;
import dev.reno.dmr.common.type.AlgorithmType;
import dev.reno.dmr.process.command.Command;
import dev.reno.dmr.process.command.CreateCommand;
import dev.reno.dmr.process.command.ProcessCommand;
import dev.reno.dmr.process.command.ResultCommand;
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