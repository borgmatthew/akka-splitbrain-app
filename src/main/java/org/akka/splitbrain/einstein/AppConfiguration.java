package org.akka.splitbrain.einstein;

import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private static final String EINSTEIN = "Einstein";

    @Bean
    public ActorSystem actorSystem() {
        final ActorSystem actorSystem = ActorSystem.create(EINSTEIN);
        actorSystem.registerOnTermination(() -> System.exit(1));
        return actorSystem;
    }
}