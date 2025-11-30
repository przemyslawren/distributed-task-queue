package dev.reno.dtq.process.behavior;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import dev.reno.dtq.common.model.JobResult;
import dev.reno.dtq.common.model.ResponseJobDto;
import dev.reno.dtq.process.algorithm.InsertionSort;
import dev.reno.dtq.process.command.Command;
import dev.reno.dtq.process.command.ProcessCommand;
import dev.reno.dtq.process.command.ResultCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class WorkerBehavior extends AbstractBehavior<Command> {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerBehavior.class);

    private WorkerBehavior(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> create() {
        return Behaviors.setup(WorkerBehavior::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(ProcessCommand.class, command -> {
                    LOG.info("Command received: {}", command);
                    LOG.info("Processing started");
                    JobResult result = processPayload(command.job());
                    LOG.info("Processing finished");

                    command.sender().tell(new ResultCommand(result));
                    return this;
                })
                .onAnyMessage(command -> {
                    LOG.info("Wrong command received in worker: {}", command);
                    return this;
                })
                .build();
    }

    private JobResult processPayload(ResponseJobDto job) {
        switch (job.algorithm()) {
            case BUBBLE_SORT, SELECTION_SORT, SHELL_SORT, HEAP_SORT, QUICK_SORT, MERGE_SORT, TIM_SORT, COUNTING_SORT,
                 BUCKET_SORT, RADIX_SORT -> {
            }
            case INSERTION_SORT -> {
                Instant now = Instant.now();
                int[] result = InsertionSort.sort(job.payload());
                Instant end = Instant.now();
                long duration = Duration.between(now, end).toNanos();

                return new JobResult(result, duration);
            }
        }
        return null;
    }
}