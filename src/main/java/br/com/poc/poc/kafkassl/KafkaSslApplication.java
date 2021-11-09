package br.com.poc.poc.kafkassl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootApplication
public class KafkaSslApplication {

    public static void main(String[] args) throws IOException {
        System.out.println(new ClassPathResource("").getFile().getAbsolutePath());
        SpringApplication.run(KafkaSslApplication.class, args);
    }
}
