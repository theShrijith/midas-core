Midas Core - J.P. Morgan Software Engineering Forage Project

A backend microservice that processes financial transactions and exposes user account balances via a REST API, as part of the JPMorgan Chase Software Engineering Virtual Experience.

📌 Overview

This project simulates a financial application ("Midas") core engine that handles user-to-user transactions. You have implemented a robust Spring Boot backend that consumes transactions, updates balances, and provides a REST endpoint for balance lookup.

This work was part of the J.P. Morgan Forage Software Engineering Program, designed to mimic real-world tasks performed by JPMorgan Chase engineers.

✅ Tasks Completed

Task 1: File Loader

Implemented a service to load transaction files containing sender, receiver, and amount.

Task 2: Kafka Integration

Configured a Kafka producer and consumer to asynchronously forward transactions.

Verified end-to-end message queue operations.

Task 3: Transaction Forwarding

Built a transaction forwarder to send transactions to a downstream processing service.

Implemented error handling and retries.

Task 4: Balance Updates

Implemented a TransactionProcessorService to update user balances from transactions.

Maintained a persistent in-memory map of user balances.

Task 5: REST API for Balances

Added a /balance?userId={} REST endpoint.

Integrated the endpoint into the existing Spring Boot app (Midas Core).

Returned user balances in JSON using the Balance class.

Returned zero if user not found.

🔧 Tech Stack

Java 17

Spring Boot 3.2

Apache Kafka (Embedded for testing)

JUnit 5 for testing

Maven for build and dependency management

H2 in-memory DB (for user data)
