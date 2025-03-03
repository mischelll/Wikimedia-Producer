package com.kafka.basics.wikimedia;

import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerProperties {
    private static final String BOOTSTRAP_SERVERS_KEY = "bootstrap.servers";
    private static final String KEY_SERIALIZER_CLASS_KEY = "key.serializer";
    private static final String VALUE_SERIALIZER_CLASS_KEY = "value.serializer";

    private static final String BOOTSTRAP_SERVERS_VALUE = "localhost:9092";
    private static final String KEY_SERIALIZER_CLASS_VALUE = StringSerializer.class.getName();
    private static final String VALUE_SERIALIZER_CLASS_VALUE = StringSerializer.class.getName();

    public Properties getProducerProperties() {
        var props = new Properties();
        props.put(BOOTSTRAP_SERVERS_KEY, BOOTSTRAP_SERVERS_VALUE);
        props.put(KEY_SERIALIZER_CLASS_KEY, KEY_SERIALIZER_CLASS_VALUE);
        props.put(VALUE_SERIALIZER_CLASS_KEY, VALUE_SERIALIZER_CLASS_VALUE);

        return props;
    }
}
