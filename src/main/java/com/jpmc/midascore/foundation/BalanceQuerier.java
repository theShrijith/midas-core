package com.jpmc.midascore.foundation;

import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.User;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class BalanceQuerier {

    private final UserRepository userRepository;

    public BalanceQuerier(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Balance query(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new Balance(user.getBalance()))
                .orElse(new Balance(0.0F)); // default 0 if not found
    }
}
