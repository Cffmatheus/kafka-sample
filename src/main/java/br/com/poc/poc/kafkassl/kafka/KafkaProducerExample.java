package br.com.poc.poc.kafkassl.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class KafkaProducerExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerExample.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void init() throws InterruptedException {

        for (int i = 0; i<10; i++){
            String msg = i + " Message from java code " + new Date();
            System.out.println(msg);
            kafkaTemplate.send("test", msg)
                    .addCallback(
                    result -> LOGGER.info("Message sent to topic: {}", msg),
                    ex -> LOGGER.error("Failed to send message", ex)
            );
            Thread.sleep(1000);
        }
    }
}
