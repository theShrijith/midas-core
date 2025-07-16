package com.jpmc.midascore.service;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.jpmc.midascore.TransactionForwarder;


@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final TransactionForwarder transactionForwarder;

    public TransactionService(UserRepository userRepository, TransactionForwarder transactionForwarder) {
        this.userRepository = userRepository;
        this.transactionForwarder = transactionForwarder;
    }

    public void process(Transaction transaction) {
        UserRecord sender = userRepository.findById(transaction.getSenderId()).orElse(null);
        UserRecord recipient = userRepository.findById(transaction.getRecipientId()).orElse(null);

        if (sender == null || recipient == null || sender.getBalance() < transaction.getAmount()) {
            return;
        }

        sender.setBalance(sender.getBalance() - transaction.getAmount());
        recipient.setBalance(recipient.getBalance() + transaction.getAmount());

        userRepository.save(sender);
        userRepository.save(recipient);

        transactionForwarder.forward(transaction);
    }
}
