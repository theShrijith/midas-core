package com.jpmc.midascore.kafka;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private TransactionService transactionService;

    @KafkaListener(topics = "${midas.transaction-topic}", groupId = "midas-core-group")
    public void listen(Transaction transaction) {
        transactionService.process(transaction);
    }

}
