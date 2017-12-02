package org.akka.splitbrain.einstein.cluster.sharding;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import org.akka.splitbrain.einstein.cluster.actor.PersonActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EinsteinClusterSharding {

    private final ActorRef shardRegion;

    @Autowired
    public EinsteinClusterSharding(final ActorSystem actorSystem) {
        final ClusterShardingSettings settings = ClusterShardingSettings.create(actorSystem);
        shardRegion = ClusterSharding.get(actorSystem).start("Einstein",
                Props.create(PersonActor.class), settings, new EinsteinMessageExtractor());
    }

    public ActorRef getShardRegion() {
        return shardRegion;
    }
}
