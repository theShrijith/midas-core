package com.jpmc.midascore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;
    private String name;
    private Float balance;

    public User() {}

    public User(Long id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance.floatValue(); // safely convert
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
