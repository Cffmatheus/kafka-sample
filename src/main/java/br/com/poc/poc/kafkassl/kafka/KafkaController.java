package br.com.poc.poc.kafkassl.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    KafkaProducerSSLExample producer;

    @GetMapping(value = "/producer")
    public String producer() throws InterruptedException {
        producer.init();

        return "Executing";
    }
}
