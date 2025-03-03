package com.kafka.basics;

import com.kafka.basics.wikimedia.ChangeProducer;
import com.kafka.basics.wikimedia.ProducerProperties;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var producer = new ChangeProducer(new ProducerProperties());
        producer.startProducer();
    }
}