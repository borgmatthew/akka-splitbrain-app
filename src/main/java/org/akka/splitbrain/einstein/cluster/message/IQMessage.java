package org.akka.splitbrain.einstein.cluster.message;

import java.io.Serializable;

public interface IQMessage extends Serializable {
    String getName();
}