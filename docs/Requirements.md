# Stakeholders

The following stakeholders interact with or are affected by the ShopSphere platform:

 Stakeholder	        Responsibility
Customer	        Browse products, manage cart, place orders, complete payments, and submit reviews.

Administrator	    Manage users, products, categories, inventory, coupons, and monitor the platform.

Warehouse	    Maintain inventory levels and fulfill product availability.
Delivery	    Process and deliver customer orders.

Support	Assist customers with order-related and account-related issues.

Payment Provider	Process and verify payment transactions.

* Functional Requirements

The system shall provide the following core business capabilities:

User registration and authentication.
Product catalog browsing and searching.
Product category management.
Shopping cart management.
Product inventory management.
Order placement and order management.
Payment processing (mock implementation).
Coupon application during checkout.
Product reviews and ratings.
Customer address management.
Email notifications.
Analytics dashboard APIs.


* Non-Functional Requirements

Requirement	                Description
Performance	        Deliver responsive product browsing, searching, and checkout operations.
Security	        Protect APIs using JWT authentication, password encryption, and role-based authorization.
Scalability	        Support future migration from a modular monolith to microservices.
Availability	    Maintain reliable access to business services.
Reliability	        Ensure transactional consistency and prevent duplicate or inconsistent business operations.
Maintainability	    Follow clean architecture, modular design, and consistent coding standards.
Logging	            Record application events for monitoring and troubleshooting.
Auditability	    Maintain audit information for important business entities and operations.


# Business Rules

The following business rules govern the application's behavior:

Email addresses must be unique.
Passwords must be stored in encrypted form.
Products belong to exactly one category.
Product prices cannot be negative.
Product SKUs must be unique.
Products may contain multiple images.
Inventory quantity cannot be negative.
Out-of-stock products cannot be purchased.
Each customer has one active shopping cart.
Adding an existing product to the cart updates its quantity instead of creating a duplicate item.
Cart quantity cannot exceed available inventory.
Orders cannot be modified after placement.
Orders may be cancelled only before shipment.
Coupons are subject to expiration and usage limits.
Only customers who have purchased a product may submit a review.
A customer may submit only one review per product.

# Assumptions

The initial version of ShopSphere is developed with the following assumptions:

The platform follows a Business-to-Consumer (B2C) business model.
Products are owned and sold directly by the company.
A single currency is supported.
A single warehouse is managed initially.
Payments are limited to digital payment methods.
Marketplace functionality and third-party sellers are outside the initial scope.

# Constraints

The project is developed under the following technical constraints:

Category	            Constraint
Backend Framework	Spring Boot
Database	        PostgreSQL
Authentication	    JWT
API Style	        RESTful APIs
Architecture	    Modular Monolith
Build Tool	        Maven
Database Migration	Flyway