package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final String topic;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaProducer(@Value("${midas.transaction-topic}") String topic,
                         KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String transactionLine) {
        try {
            String[] parts = transactionLine.split(", ");
            if (parts.length != 3) {
                logger.warn("Invalid transaction line format: {}", transactionLine);
                return;
            }

            long senderId = Long.parseLong(parts[0]);
            long recipientId = Long.parseLong(parts[1]);
            float amount = Float.parseFloat(parts[2]);

            Transaction transaction = new Transaction(senderId, recipientId, amount);
            logger.info("Sending transaction to Kafka: {}", transaction);

            kafkaTemplate.send(topic, transaction);

        } catch (Exception e) {
            logger.error("Failed to parse and send transaction line: {}", transactionLine, e);
        }
    }
}
