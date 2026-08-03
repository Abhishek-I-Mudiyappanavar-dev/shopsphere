# Testing Strategy

This document outlines the testing approach for ShopSphere. At the current stage, no tests have been implemented. The following testing strategies are planned for future development milestones.

---

# Planned Strategy

## Unit Testing

Individual components such as services and utility classes will be tested in isolation to verify business logic.

## Integration Testing

Integration tests will validate the interaction between application layers, the persistence layer, and the database.

## Mockito

Mockito will be used to mock external dependencies during unit testing, allowing business logic to be tested independently.

## JUnit

JUnit will serve as the primary testing framework for writing and executing automated tests.

## Testcontainers

Testcontainers will provide isolated PostgreSQL containers for integration testing, ensuring tests execute against a production-like database environment.