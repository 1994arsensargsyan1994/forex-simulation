# Forex Simulation – Multi-Module Project

This repo contains a **Spring Boot microservice system** with API Gateway + Forex Simulation service, structured as a **Gradle multi-module build**.

---
Requirements: Java 17+, Gradle, PostgreSQL

---

## 📂 Project Structure

```
forex-simulation
├─ api-gateway                      # Spring Cloud Gateway + Swagger + Security & Rate limiting
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
│   ├─ forex-simulation-service-core# Domain services (customer, account, rates)
│   └─ forex-simulation-service-impl# Implementations (REST controllers, schedulers, runners)
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
  - Must implement **Security** (authentication/authorization) and **Rate Limiting** to protect backend services.
- **forex-simulation-api**
  - Defines API **interfaces**, **models**, and **contracts**.
- **forex-simulation-service**
  - Handles **business rules** and **persistence** (JPA + Flyway).
- **common**
  - Shared DTOs, error handling, and utility classes.

---

## ✅ Implemented Flows

- **Customer creation** — persist new customers, unique email enforced.
- **Customer details lookup** — fetch customer with all accounts.
- **Account creation** — create account for a given customer.
- **Rates seeding (CommandLineRunner)** — seeds initial rates at startup.
- **Get list of rates** — REST endpoint to fetch all rates.
- **Scheduled rate updates** — randomizes rates every N seconds (configurable in properties).

---

## 📡 API Endpoints (via Gateway)

- `POST /customer` → create customer
- `GET /customer/{id}/details` → lookup customer with accounts
- `POST /account/{customerId}` → create account for customer
- `GET /rates` → list all rates

Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ▶️ Running Locally

1. Start PostgreSQL (db: `forex_simulation`).
3. Start `forex-simulation-service-impl`.
4. Start `api-gateway`.
5. Access Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📝 Notes

- The service uses **UUID** for `account.number`.
- Customers can have **multiple accounts**, including **different currencies type**.
- Rates are updated randomly by the scheduler using the configured interval.
- **API Gateway must implement:**
  - Security (authentication/authorization layer)
  - Rate Limiting (prevent abuse and DoS)
  - Request validation before forwarding to services

---

## 🛡️ API Gateway Hardening (Security, Rate Limiting, etc.)

The **API Gateway** is the only public entrypoint. Harden it and keep the service private.

### Security must-haves
- **Authentication & Authorization** (JWT/OAuth2/OpenID Connect). Protect all `/forex/api/**` routes.
- **Zero trust to service**: add a shared header from Gateway → Service (service rejects requests without it).
- **CORS**: restrict allowed origins/methods/headers.
- **TLS/HTTPS**: terminate TLS at the gateway (or a load balancer in front).
- **Input validation**: size limits, content-type checks, JSON schema if needed.
- **Hide internals**: remove/obfuscate server banners, detailed errors.
