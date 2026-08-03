
# REST Philosophy

ShopSphere follows REST (Representational State Transfer) principles to provide a consistent, predictable, and resource-oriented API.

The API is designed around business resources rather than actions, allowing clients to interact with the system using standard HTTP methods.

* Design Principles
* Resource-oriented URIs
* Stateless communication
* Standard HTTP methods
* Consistent request and response formats
* Predictable HTTP status codes
* Explicit API versioning
* Pagination and filtering for collection resources
* Standardized error responses


# URI Design

URIs represent business resources rather than operations.

# Resource Naming

Use plural nouns for collections.

  Resource	            URI

* Authentication	/api/v1/auth
* Users	            /api/v1/users
* Products	        /api/v1/products
* Categories	    /api/v1/categories
* Cart	            /api/v1/cart
* Orders	        /api/v1/orders
* Payments	        /api/v1/payments
* Coupons	        /api/v1/coupons
* Reviews	        /api/v1/reviews
* Addresses	        /api/v1/addresses

# URI Guidelines

* Use nouns instead of verbs.
* Use lowercase paths.
* Use resource identifiers for individual resources.
* Use nested resources only where ownership is explicit (for example, product reviews).

Examples:

GET    /api/v1/products
GET    /api/v1/products/{id}
POST   /api/v1/products
PATCH  /api/v1/orders/{id}/cancel
GET    /api/v1/products/{id}/reviews

# Versioning

ShopSphere adopts URI-based API versioning.

All public endpoints are prefixed with the API version.

/api/v1

Future breaking changes will be introduced through a new version (for example, /api/v2) while maintaining compatibility for existing clients during the transition period.

This versioning strategy is documented in ADR-006.

# HTTP Methods

Method	        Purpose
GET	        Retrieve resources
POST	    Create a new resource
PUT	        Replace an existing resource
PATCH	    Partially update a resource or perform a supported business action
DELETE	    Remove a resource

The API uses standard HTTP semantics to ensure consistency and predictability.

# Status Codes

Status Code	                    Description
200 OK	                    Request completed successfully
201 Created	                Resource created successfully
204 No Content	            Request completed with no response body
400 Bad Request	            Invalid request data
401 Unauthorized	        Authentication required or invalid credentials
403 Forbidden	            Authenticated but insufficient permissions
404 Not Found	            Requested resource does not exist
409 Conflict	            Business rule or uniqueness conflict
500 Internal Server Error	Unexpected server error

# Pagination

Collection resources support pagination to avoid returning excessively large result sets.

# Query Parameters

  Parameter	    Description

* page	    Zero-based page number
* size	    Number of records per page
* sort	    Sorting criteria

Example:

GET /api/v1/products?page=0&size=20&sort=price,asc


# Filtering

Collection resources support filtering using query parameters.

Examples include filtering by category, brand, price range, rating, and other resource-specific criteria.

Example:

GET /api/v1/products?category=electronics
GET /api/v1/products?minPrice=1000
GET /api/v1/products?maxPrice=5000
GET /api/v1/products?rating=4
GET /api/v1/products?sort=price

Multiple filters may be combined within a single request where supported.

# Error Response Format

All API errors follow a consistent response structure.

{
  "timestamp": "2026-08-01T18:30:20",
  "status": 404,
  "error": "Not Found",
  "message": "Product not found",
  "path": "/api/v1/products/100"
}

This standard response format enables clients to handle errors consistently across all endpoints.

# Authentication Flow

ShopSphere secures protected resources using JWT-based authentication.

The authentication workflow consists of:

User registration.
User login.
Server validates credentials.
JWT is issued upon successful authentication.
Client includes the JWT in the Authorization header for subsequent requests.
Protected endpoints validate the token before processing the request.

Authentication-related endpoints are exposed under:

/api/v1/auth