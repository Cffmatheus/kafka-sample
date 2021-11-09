package br.com.poc.poc.kafkassl.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerExample {

    static final String topic = "test";
    static final String group = "test_group";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", group);
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props)) {
            consumer.subscribe(Arrays.asList(topic));

            for (int i = 0; i < 500; i ++) {
                ConsumerRecords<String, String> records = consumer.poll(1000L);
                System.out.println("Size " +records.count());
            }
        }
        System.out.println("END");
    }
}