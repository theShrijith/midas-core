package com.jpmc.midascore.controller;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BalanceController {

    private final UserRepository userRecordRepository;

    public BalanceController(UserRepository userRecordRepository) {
        this.userRecordRepository = userRecordRepository;
    }

    @GetMapping("/balance")
    public String getBalance(@RequestParam("user") String userName) {
        Optional<UserRecord> userRecordOpt = userRecordRepository.findByName(userName);
        if (userRecordOpt.isPresent()) {
            double balance = userRecordOpt.get().getBalance();
            return String.valueOf((int) Math.floor(balance));
        } else {
            return "User not found";
        }
    }
}
