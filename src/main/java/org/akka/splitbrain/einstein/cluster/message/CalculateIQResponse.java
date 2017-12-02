package org.akka.splitbrain.einstein.cluster.message;

public final class CalculateIQResponse implements IQMessage {

    private final String name;
    private final int iq;

    public CalculateIQResponse(String name, int iq) {
        this.name = name;
        this.iq = iq;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getIq() {
        return iq;
    }
}
