package br.com.poc.poc.kafkassl.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaProducerSSLExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerExample.class);

    public void init() throws InterruptedException {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
        configProps.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
        KafkaTemplate<String, String> template = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));

        for (int i = 0; i<100; i++){
            String msg = i + " Message from java code " + new Date();
            System.out.println(msg);
            template.send("test", msg)
                    .addCallback(
                    result -> LOGGER.info("Message sent to topic: {}", msg),
                    ex -> LOGGER.error("Failed to send message", ex)
            );;
            Thread.sleep(1000);
        }

        template.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        KafkaProducerSSLExample producer = new KafkaProducerSSLExample();
        producer.init();
    }

}
