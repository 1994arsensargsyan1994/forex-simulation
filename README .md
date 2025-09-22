# Forex Simulation – Multi-Module Project

This repo contains a **Spring Boot microservice system** with API Gateway + Forex Simulation service, structured as a **Gradle multi-module build**.

---

## 📂 Project Structure

```
forex-simulation
├─ api-gateway                      # Spring Cloud Gateway + Swagger
│
├─ common                           # Shared modules (DTOs, API models, core utils)
│   ├─ common-api-model
│   ├─ common-core
│   └─ common-dto
│
├─ forex-simulation-api             # Public API contracts
│   ├─ forex-simulation-api-app     # Spring Boot app runner (API layer)
│   ├─ forex-simulation-api-facade  # Facade layer (client to service)
│   ├─ forex-simulation-api-model   # Request/Response DTOs
│   └─ forex-simulation-api-rest    # REST client + resource contracts
│
├─ forex-simulation-service         # Business logic & persistence
│   ├─ forex-simulation-migration   # Flyway migrations (DB schemas)
│   ├─ forex-simulation-persistence # JPA entities, repositories
│   ├─ forex-simulation-service-core# Domain services (customer, account, orders)
│   └─ forex-simulation-service-impl# Implementations
│
├─ build.gradle
├─ settings.gradle
└─ README.md
```

---

## ⚙️ Architecture Overview

```
[ Client ] → [ api-gateway ] → [ forex-simulation-api ] → [ forex-simulation-service ] → [ PostgreSQL ]
```

- **api-gateway**
  - Exposes REST endpoints.
  - Provides **Swagger UI** for docs.
  - Forwards requests to downstream `forex-simulation`.

- **forex-simulation-api**
  - Defines API **interfaces**, **models**, and **contracts**.
  - Separates API layer from core business logic.

- **forex-simulation-service**
  - Handles **business rules** and **persistence**.
  - Includes `migration` for DB schema (Flyway SQL).

- **common**
  - Shared DTOs, error handling, and utility classes.

---

## 🗄️ Database Schema (Flyway: `forex-simulation-migration`)

Tables required to handle business logic:

---

## 📡 API Endpoints

All routes go through **Gateway**:

- `POST /customer` → create a customer
- `GET /customer/{id}/details` → lookup customer + accounts
- `POST /account/{customerId}` → create account for customer

**Swagger (gateway):**  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ▶️ Running Locally

1. Start **Postgres** (via Docker): db name forex_simulation
2. Run migrations (`forex-simulation-migration` → Flyway).
3. Start **forex-simulation-service** (`:forex-simulation-service-impl`).
4. Start **api-gateway**.
5. Open Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
