package com.kafka.basics.wikimedia;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeHandler implements BackgroundEventHandler {

    private final Logger log = LoggerFactory.getLogger(ChangeHandler.class);
    private final String topic;
    private final KafkaProducer<String, String> producer;

    public ChangeHandler(KafkaProducer<String, String> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    @Override
    public void onOpen() {
        // nothing
    }

    @Override
    public void onClosed() {
        producer.close();
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) {
        log.info("Received a new message: {}", messageEvent);
        // async
        producer.send(new ProducerRecord<>(topic, messageEvent.getData()));
    }

    @Override
    public void onComment(String s) {
        // nothing
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error occurred: {}", throwable.getLocalizedMessage());
    }
}
