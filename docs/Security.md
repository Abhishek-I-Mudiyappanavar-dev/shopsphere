# Security Strategy

This document describes the security mechanisms adopted by ShopSphere. At the current stage, the security features listed below are planned and will be implemented in future milestones.

---

# Planned

## JWT (JSON Web Token)

JWT will be used for stateless authentication, allowing clients to securely access protected REST APIs after successful login.

## Refresh Tokens

Refresh tokens will be used to obtain new access tokens without requiring users to authenticate again, improving both security and user experience.

## Role-Based Access Control (RBAC)

Access to protected resources will be controlled using roles such as Customer and Administrator, ensuring users can perform only the operations permitted by their assigned roles.

## BCrypt Password Hashing

User passwords will be hashed using BCrypt before storage to prevent plaintext password persistence and protect user credentials.

## Rate Limiting

Rate limiting will be introduced to protect authentication endpoints and public APIs against brute-force attacks and excessive request traffic.