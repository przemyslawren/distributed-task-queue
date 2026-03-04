package dev.reno.dmr.process.config;

import org.apache.pekko.actor.typed.ActorSystem;
import dev.reno.dmr.process.behavior.ManagerBehavior;
import dev.reno.dmr.process.command.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    @Bean
    public ActorSystem<Command> createActorSystem() {
        return ActorSystem.create(ManagerBehavior.create(), "ProcessSystem");
    }
}