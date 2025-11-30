package dev.reno.dtq.process.config;

import akka.actor.typed.ActorSystem;
import dev.reno.dtq.process.behavior.ManagerBehavior;
import dev.reno.dtq.process.command.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    @Bean
    public ActorSystem<Command> createActorSystem() {
        return ActorSystem.create(ManagerBehavior.create(), "ProcessSystem");
    }
}