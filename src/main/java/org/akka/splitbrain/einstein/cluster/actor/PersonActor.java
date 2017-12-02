package org.akka.splitbrain.einstein.cluster.actor;

import akka.actor.AbstractLoggingActor;
import org.akka.splitbrain.einstein.cluster.message.CalculateIQRequest;
import org.akka.splitbrain.einstein.cluster.message.CalculateIQResponse;

import java.util.Random;

public class PersonActor extends AbstractLoggingActor {

    private Integer iq = null;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CalculateIQRequest.class, this::calculateIQ)
                .matchAny(message -> log().info("Received some message")).build();
    }

    private void calculateIQ(final CalculateIQRequest message) {
        if (iq == null) {
            iq = new Random().nextInt(100) + 100;
        }
        sender().tell(new CalculateIQResponse(message.getName(), iq), getSelf());
    }

}
