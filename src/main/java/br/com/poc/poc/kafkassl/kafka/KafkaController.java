package br.com.poc.poc.kafkassl.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    KafkaProducerExample producer;

    @PostMapping(value = "/producer")
    public String producerSSL() throws InterruptedException {
        producer.init();
        return "Executed";
    }
}
