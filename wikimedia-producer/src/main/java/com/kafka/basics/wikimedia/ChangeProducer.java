package com.kafka.basics.wikimedia;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class ChangeProducer {

    private final ProducerProperties producerProperties;
    private final KafkaProducer<String, String> kafkaProducer;

    public ChangeProducer(ProducerProperties producerProperties) {
        this.producerProperties = producerProperties;
        this.kafkaProducer = new KafkaProducer<>(this.producerProperties.getProducerProperties());
    }


    public void startProducer() throws InterruptedException {
        var eventHandler = new ChangeHandler(kafkaProducer, "wikimedia.recentchange");
        var url = "https://stream.wikimedia.org/v2/stream/mediawiki.recentchange";

        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, new EventSource.Builder(URI.create(url)));
        BackgroundEventSource eventSource = builder.build();


        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }



}
