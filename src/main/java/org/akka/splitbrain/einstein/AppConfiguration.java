package org.akka.splitbrain.einstein;

import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private static final String EINSTEIN = "Einstein";

    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create(EINSTEIN);
    }
}