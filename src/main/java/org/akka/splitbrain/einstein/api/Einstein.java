package org.akka.splitbrain.einstein.api;

import akka.pattern.PatternsCS;
import org.akka.splitbrain.einstein.cluster.message.CalculateIQRequest;
import org.akka.splitbrain.einstein.cluster.message.CalculateIQResponse;
import org.akka.splitbrain.einstein.cluster.sharding.EinsteinClusterSharding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("iq")
public class Einstein {

    private static final Logger logger = LoggerFactory.getLogger(Einstein.class);
    private static final long TIMEOUT_MILLIS = 2000L;

    private final EinsteinClusterSharding cluster;

    @Autowired
    public Einstein(final EinsteinClusterSharding cluster) {
        this.cluster = cluster;
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public int iq(@PathVariable("name") final String name) throws ExecutionException, InterruptedException {
        logger.info("Calculating IQ for {}", name);
        final CalculateIQResponse iqResponse = (CalculateIQResponse) PatternsCS.ask(cluster.getShardRegion(), new CalculateIQRequest(name), TIMEOUT_MILLIS)
                .toCompletableFuture().get();
        return iqResponse.getIq();
    }
} 
