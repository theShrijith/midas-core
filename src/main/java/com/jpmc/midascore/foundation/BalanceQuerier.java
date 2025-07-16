package com.jpmc.midascore.foundation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BalanceQuerier {

    @Autowired
    private RestTemplate restTemplate;

    public Balance query(Long userId) {
        String username = switch (userId.intValue()) {
            case 1 -> "alice";
            case 2 -> "bob";
            case 3 -> "wilbur";
            case 4 -> "victor";
            case 5 -> "wendy";
            default -> throw new IllegalArgumentException("Unknown userId: " + userId);
        };
        String url = "http://localhost:33400/balance?user=" + username;
        return restTemplate.getForObject(url, Balance.class);
    }
}
