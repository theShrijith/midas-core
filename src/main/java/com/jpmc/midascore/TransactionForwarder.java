package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionForwarder {

    private static final Logger logger = LoggerFactory.getLogger(TransactionForwarder.class);
    private final RestTemplate restTemplate;
    private final String incentiveApiUrl;
    private final UserRepository userRepository;

    public TransactionForwarder(RestTemplate restTemplate,
                                @Value("${midas.incentive-api-url:http://localhost:33400/incentives}") String incentiveApiUrl,
                                UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.incentiveApiUrl = incentiveApiUrl;
        this.userRepository = userRepository;
    }

    public void forward(Transaction transaction) {
        try {
            Double incentive = restTemplate.postForObject(incentiveApiUrl, transaction, Double.class);
            logger.info("Received incentive: {}", incentive);

            if (incentive != null) {
                transaction.setIncentive(incentive);
                UserRecord recipient = userRepository.findById(transaction.getRecipientId()).orElse(null);
                if (recipient != null) {
                    recipient.setBalance(recipient.getBalance() + incentive.floatValue());
                    userRepository.save(recipient);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to forward transaction to incentive API", e);
        }
    }
}
