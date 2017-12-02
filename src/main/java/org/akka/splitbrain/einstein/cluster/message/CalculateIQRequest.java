package org.akka.splitbrain.einstein.cluster.message;

public final class CalculateIQRequest implements IQMessage {

    private final String name;

    public CalculateIQRequest(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
