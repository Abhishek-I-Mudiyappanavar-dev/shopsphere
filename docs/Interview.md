Why Repository?

What is Modular Monolith?

Why Package by Feature?

Difference between DAO and Repository?

What is API Versioning?

Why Flyway?

Difference between Layered Architecture and Clean Architecture?

# Requirement Analysis

1. Why did you choose to build a B2C e-commerce platform instead of a marketplace?

2. How did you decide the project scope?

3. Why are business rules improtant?

# Domain Modeling

4. What is domain modeling?

5. why did you divide the application into business domains?

6. Why is Analytics treated as a separate domain instead of a database table?

# System Design

7. Why did you choose a layered architecture?

8. Why did you choose a Modular Monolith instead of Microservices?

9. Why did you choose Package-by-Feature?

10. Why should controllers not access the database directly?

# Database Design

11. Why did you choose PostgreSQL?

12. why are UUIDs used as primary keys?

13. Why is OrderItem a separate entity instead of directly linking Orders and Products?

14. Why store the purchase price inside OrderItem?

15. Why normalize the database?

# API Design

16. Why do you use REST instead of RPC-style APIs?

17. Why use URI-based API versioning?

18. Why use plural resource names in REST APIs?

19. Why use query parameters for filtering instead of separate endpoints?

20. Why should APIs return standard HTTP status codes?

# Architecture Decisions

21. Why use the Repository Pattern?

22. Why use Flyway instead of Hibernates's automatic schema generation?

23. Why document architecture decisions using ADRs?
