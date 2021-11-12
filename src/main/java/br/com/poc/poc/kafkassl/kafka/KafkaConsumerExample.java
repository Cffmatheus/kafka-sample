package br.com.poc.poc.kafkassl.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumerExample {

    private final CountDownLatch latch = new CountDownLatch(3);

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerExample.class);

    @KafkaListener(topics = "test")
    public void listen(ConsumerRecord<?, ?> cr) {
        log.info(cr.toString());
        latch.countDown();
    }


}