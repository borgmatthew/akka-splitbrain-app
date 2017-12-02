package org.akka.splitbrain.einstein.cluster.sharding;

import akka.cluster.sharding.ShardRegion.MessageExtractor;
import org.akka.splitbrain.einstein.cluster.message.CalculateIQRequest;
import org.akka.splitbrain.einstein.cluster.message.IQMessage;

public class EinsteinMessageExtractor implements MessageExtractor {

    @Override
    public String entityId(final Object message) {
        if (message instanceof CalculateIQRequest) {
            return ((IQMessage) message).getName();
        }
        return null;
    }

    @Override
    public Object entityMessage(final Object message) {
        return message;
    }

    @Override
    public String shardId(final Object message) {
        if (message instanceof CalculateIQRequest) {
            return String.valueOf(((IQMessage) message).getName().chars().sum() % 20);
        }
        return null;
    }
}
