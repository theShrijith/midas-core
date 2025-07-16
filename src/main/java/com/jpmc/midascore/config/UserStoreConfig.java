package com.jpmc.midascore.config;

import com.jpmc.midascore.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserStoreConfig {

    @Bean
    public Map<Integer, User> userStore() {
        Map<Integer, User> store = new HashMap<>();

        store.put(1, new User(1L, "alice", 1000.0));
        store.put(2, new User(2L, "bob", 1000.0));
        store.put(3, new User(3L, "wilbur", 1000.0));

        return store;
    }
}
