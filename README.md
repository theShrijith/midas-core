# Midas
Project repo for the JPMC Advanced Software Engineering Forage program
# 💰 Midas Core

Midas Core is a key component of the Midas platform, responsible for processing financial transactions and serving real-time user balances. Built using Spring Boot and Kafka, this service acts as the backbone of the system’s transaction pipeline and user state management.

---

## 🚀 Overview

This microservice handles:

- Ingestion of financial transactions via Apache Kafka
- Real-time processing and balance updates for users
- In-memory storage of user states
- REST API exposure to retrieve current user balances

The design is intentionally lightweight and modular, optimized for rapid development, ease of testing, and future scalability.

---

## 🧩 Key Features

### 1. Kafka-Based Transaction Ingestion
- Consumes transactions from the `transactions` Kafka topic
- Transaction format: `senderId, receiverId, amount`
- Parses and validates each transaction for processing

### 2. Transaction Processing Logic
- Deducts balance from sender
- Adds balance to receiver
- Automatically creates new users if they do not exist
- Ensures consistency in user state after each transaction

### 3. User State Management
- Uses an in-memory model (`User`, `UserRecord`) to maintain:
  - Current balance
  - List of sent and received transactions

### 4. Transaction Forwarding
- Valid transactions are forwarded using a custom forwarder class
- Simulates integration with downstream services (e.g., incentives)

### 5. REST API for Balance Retrieval
- Exposes a REST endpoint:  


