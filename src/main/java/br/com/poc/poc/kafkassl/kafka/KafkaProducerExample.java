package br.com.poc.poc.kafkassl.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

@Component
public class KafkaProducerExample {

    Properties props = new Properties();

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerExample.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private void init() throws InterruptedException {
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("kafka.topic.name", "test");
        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(this.props, new StringSerializer(), new ByteArraySerializer());

        for (int i = 0; i<100; i++){
            String msg = i + " Message from java code " + new Date();
            byte[] payload = msg.getBytes();
            System.out.println(msg);
            ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>(props.getProperty("kafka.topic.name"), payload);
            producer.send(record);
            Thread.sleep(1000);
        }

        producer.close();
    }

    public static void main(String[] args) throws InterruptedException {
        KafkaProducerExample producer = new KafkaProducerExample();
        producer.init();
    }
}
