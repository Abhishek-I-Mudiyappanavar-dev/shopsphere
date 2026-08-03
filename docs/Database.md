
# Purpose

The ShopSphere database is designed to provide a consistent, normalized, and scalable persistence layer for all business domains within the application.

The database supports transactional operations across user management, product catalog, inventory, shopping cart, order processing, payments, reviews, and coupons while maintaining data integrity and historical accuracy.

The schema is designed to support the current modular monolith architecture and facilitate a future transition to microservices with minimal structural changes.

# Candidate Entities

The following entities have been identified as persistent business objects during the database design phase.

 Domain	            Entities

Identity	    User, Role, Address
Product	        Category, Product, ProductImage
Inventory	    Inventory
Shopping	    Cart, CartItem
Order	        Order, OrderItem
Payment	        Payment
Promotion	    Coupon
Review	        Review

Analytics is intentionally excluded as a persistent entity because analytical data will be derived from existing transactional data rather than stored independently.

# Relationships

The database follows the business relationships identified during domain modeling.

| Relationship           | Cardinality            |
| ---------------------- | ---------------------- |
| User → Address         | One-to-Many            |
| User → Cart            | One-to-One             |
| User → Order           | One-to-Many            |
| User → Review          | One-to-Many            |
| Category → Product     | One-to-Many            |
| Product → ProductImage | One-to-Many            |
| Product → Inventory    | One-to-One             |
| Cart → CartItem        | One-to-Many            |
| Product → CartItem     | One-to-Many            |
| Order → OrderItem      | One-to-Many            |
| Product → OrderItem    | One-to-Many            |
| Order → Payment        | One-to-One             |
| Product → Review       | One-to-Many            |
| Order → Coupon         | Many-to-One (optional) |

CartItem and OrderItem act as associative entities to resolve many-to-many relationships while storing additional business information such as quantity and purchase price.

# Normalization Strategy

The database is designed following normalization principles to reduce redundancy and maintain data consistency.

The design aims to:

* Store each business concept in a single entity.
* Avoid duplicate customer and product information.
* Maintain relationships using foreign keys.
* Preserve historical data where required.

Certain transactional data, such as product pricing at the time of purchase, is intentionally stored within OrderItem as a snapshot to preserve historical accuracy even if product information changes later.

# Primary Key Strategy

All persistent entities use UUID as their primary key.

This provides:

* Globally unique identifiers.
* Consistent identifier generation across business domains.
* Support for future migration to microservices.
* Reduced exposure of sequential identifiers through public APIs.

This decision is documented in ADR-005.

# Soft Delete Strategy

The application will prefer soft deletion for business entities that may be referenced by historical records.

Instead of physically removing records, entities may be marked as inactive or logically deleted, preserving data integrity and enabling historical reporting.

The exact implementation strategy will be finalized during the implementation phase.

# Audit Fields Strategy

Most persistent entities will include audit metadata to support traceability and maintenance.

* created_at
* updated_at
* created_by
* updated_by

These fields enable tracking of record creation and modification throughout the application's lifecycle.

# Indexing Strategy

Indexes will be created for frequently queried columns to improve query performance while avoiding unnecessary indexing overhead.

Initial index candidates include:

* User email
* Product SKU
* Product name
* Category identifier
* Order status
* Order date
* User identifier in transactional tables

Additional indexes will be introduced based on actual query patterns and performance analysis during implementation.

# Migration Strategy

Database schema changes will be managed using Flyway.

Each structural change to the database will be introduced through version-controlled migration scripts, ensuring that all environments remain synchronized and that schema evolution is fully traceable.

Migration scripts will be immutable once applied to shared environments.

This decision is documented in ADR-007.