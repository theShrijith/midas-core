package com.jpmc.midascore;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator {

    private final UserRepository userRepository;

    public UserPopulator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void populate() {
        userRepository.save(new UserRecord("wilbur", 200.0f));
        userRepository.save(new UserRecord("charlotte", 150.0f));
        // Add more users if needed
    }
}
