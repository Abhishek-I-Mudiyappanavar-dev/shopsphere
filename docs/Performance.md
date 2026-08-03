# Planned

# Performance Strategy

This document records the performance optimization techniques adopted throughout the development of ShopSphere. At the current stage, no performance optimizations have been implemented. The following strategies are planned for future milestones.

---

# Planned

## Redis

To reduce database load by storing frequently accessed application data in memory.

## Caching

To improve response times for frequently requested resources while minimizing repeated database queries.

## Database Indexes

To optimize query performance for frequently searched and filtered data.

## Asynchronous Processing

To execute non-blocking operations such as notifications and background tasks without delaying user requests.

## Connection Pool

To efficiently manage database connections and improve application throughput under concurrent load.