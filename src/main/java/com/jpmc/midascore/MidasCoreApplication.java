package com.jpmc.midascore;

import jakarta.persistence.Embedded;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableKafka
public class MidasCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidasCoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner testKafkaSend(KafkaProducer kafkaProducer) {
        return args -> kafkaProducer.send("1, 2, 100");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
