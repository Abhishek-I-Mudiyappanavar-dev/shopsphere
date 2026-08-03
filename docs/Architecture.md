
# Business Overview

ShopSphere is an enterprise-grade Business-to-Consumer (B2C) e-commerce backend designed to simulate the development practices of a professional software engineering team.

The platform enables customers to browse products, manage shopping carts, place orders, complete payments, and review purchased products. The business owns and sells all products directly to customers. Support for third-party sellers is intentionally excluded from the initial version.

The project is developed as a Modular Monolith with clearly defined business domains and is architected to support a future migration to a microservices architecture.

# Project Goals

The primary goals of ShopSphere are:

* Develop an enterprise-grade e-commerce backend using Spring Boot.
* Apply clean architecture and modular design principles.
* Build a scalable and maintainable codebase.
* Establish clear boundaries between business domains.
* Implement secure REST APIs following industry best practices.
* Design a relational database that preserves data integrity and supports future growth.
* Prepare the application for a gradual transition from a modular monolith to microservices.

# Architecture

ShopSphere adopts a layered architecture combined with package-by-feature organization inside a modular monolith.

* Layered Architecture

The application separates responsibilities into distinct layers:

Controller Layer
Service Layer
Repository Layer
Database Layer

Each layer has a single responsibility and communicates only with adjacent layers.


* Package by Feature

The source code is organized around business capabilities rather than technical layers.

Each feature module contains its own controllers, services, repositories, entities, DTOs, mappers, exceptions, and validators.

This structure improves maintainability and keeps related components together.


* Modular Monolith

All business modules are deployed as a single Spring Boot application while remaining logically independent.

This architecture simplifies development while preserving clear module boundaries, allowing individual modules to be extracted into microservices in future iterations.

# Business Domain
The system is organized into the following business domains:

| Domain         | Responsibility                                |
| -------------- | --------------------------------------------- |
| Authentication | User authentication and authorization         |
| User           | Customer profile and address management       |
| Product        | Product catalog and product information       |
| Inventory      | Stock management and availability             |
| Cart           | Temporary shopping cart management            |
| Order          | Order lifecycle and purchase records          |
| Payment        | Payment processing and transaction management |
| Review         | Product ratings and customer reviews          |
| Notification   | Customer communication and notifications      |
| Analytics      | Sales reporting and business metrics          |

Each domain encapsulates a single business responsibility and collaborates with other domains through well-defined service boundaries.

# Request Flow

Client

↓

Security

↓

Controller

↓

Service

↓

Repository

↓

PostgreSQL Database

* The request is processed in the following order:

1. The client sends an HTTP request.
2. Spring Security authenticates and authorizes the request.
3. The controller receives and validates the request.
4. The service layer executes the business logic.
5. The repository layer performs the required database operations.
6. PostgreSQL returns the requested data.
7. The response propagates back through the application to the client.

# Cross-Cutting Concerns

The following concerns apply across multiple modules and layers:

* Authentication and Authorization
* Request Validation
* Logging
* Exception Handling
* Transaction Management
* Auditing
* Caching (planned)