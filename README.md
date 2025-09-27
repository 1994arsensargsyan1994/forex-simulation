# Forex – API Guide & Step‑by‑Step Walkthrough

A **Spring Boot** microservice system with **API Gateway + Forex service** (Gradle multi‑module). This README documents the **request paths** and **response formats**, plus a **step‑by‑step flow** to try the system end‑to‑end.

> Base URL (Gateway): `http://localhost:8081`  
> Swagger: `http://localhost:8081/swagger-ui/index.html`

---
Requirements: Java 17+, Gradle, Docker, PostgreSQL
---
🚀 How to Run the Project

This project runs as a multi-service stack using Docker Compose.
It includes:
	•	Postgres database (forex_db)
	•	forex-api (backend service, hidden behind gateway)
	•	api-gateway (Spring Cloud Gateway, exposed to host on port 8081)
  
2️⃣ Build Images
From the project root:

docker compose build

3️⃣ Start Services

docker compose up

---
## Conventions

- **Success envelope**
```json
{ "successful": true, ... }
```
- **Failure envelope** (HTTP 200 in this API)
```json
{ "successful": false, "failures": [ { "code": "<failure.code>", "reason": "<human readable>" } ] }
```
- **Idempotency** (Orders): Header `Idempotency-Key: <your-key>`
- **Validation**: request size limits, not‑null checks, email format

---

## Endpoints

### 1) Create Customer
**POST** `/customer`  
**Request**
```json
{ "name": "user", "email": "user@gmail.com", "dateOfBirth": "1998-09-23" }
```
**Success (200)**
```json
{ "customerId": 2, "successful": true }
```

### 2) Lookup Customer Details
**GET** `/customer/{id}/details`  
**Success (200)**
```json
{
  "successful": true,
  "details": { "id": 1, "username": "arsen", "email": "arsen@gmail.com", "dateOfBirth": "1998-09-23" }
}
```
**Not Found (200)**
```json
{ "successful": false, "failures": [ { "code": "failure.customer.not.found", "reason": "Customer not found." } ] }
```

### 3) Create Account
**POST** `/account/{customerId}`  
**Request**
```json
{ "currency": "USD", "balance": 250 }
```
- A customer can have **multiple accounts**, **one per currency**.

**Success (200)**
```json
{ "number": "d0140876-7343-4a4c-8d52-8de8d914812b", "successful": true }
```
**Duplicate currency (200)**
```json
{ "successful": false, "failures": [ { "code": "failure.account.already.exists", "reason": "Another Account already exists for currency type" } ] }
```
**Customer not found (200)**
```json
{ "successful": false, "failures": [ { "code": "failure.customer.not.found", "reason": "Customer not found." } ] }
```

### 4) Lookup Customer Accounts
**GET** `/customer/{id}/account`  
**Success (200)**
```json
{
  "successful": true,
  "count": 2,
  "accounts": [
    { "disabled": false, "number": "61a188e7-e800-4883-8e42-87374ccadbf9", "balance": 250, "isDisabled": false, "currency": "USD" },
    { "disabled": false, "number": "d0140876-7343-4a4c-8d52-8de8d914812b", "balance": 250, "isDisabled": false, "currency": "AMD" }
  ]
}
```
**Customer not found (200)**
```json
{ "successful": false, "failures": [ { "code": "failure.customer.not.found", "reason": "Customer not found." } ] }
```

### 5) List Rates
**GET** `/rates`  
**Success (200)**
```json
{
  "successful": true,
  "count": 8,
  "rates": [
    { "id": 1, "from": "USD", "to": "AMD", "rate": 390 },
    { "id": 2, "from": "AMD", "to": "USD", "rate": 0.0026 },
    { "id": 3, "from": "USD", "to": "RUB", "rate": 95 },
    { "id": 4, "from": "RUB", "to": "USD", "rate": 0.0105 },
    { "id": 5, "from": "USD", "to": "GBP", "rate": 0.78 },
    { "id": 6, "from": "GBP", "to": "USD", "rate": 1.28 },
    { "id": 7, "from": "USD", "to": "JPY", "rate": 149 },
    { "id": 8, "from": "JPY", "to": "USD", "rate": 0.0067 }
  ]
}
```

### 6) Create Order
**POST** `/orders`  
**Headers**: `Idempotency-Key: <your-key>`  
**Request**
```json
{ "fromAccountId": "61a188e7-e800-4883-8e42-87374ccadbf9", "toAccountId": "d0140876-7343-4a4c-8d52-8de8d914812b", "amount": 25 }
```
**Success (200)**
```json
{
	"id": 402,
	"idempotencyKey": "po28",
	"status": "FAILED",
	"failureReason": "INSUFFICIENT_FUNDS",
	"created": true,
	"successful": true
}
```
or in same case we keep failed order in db with FAILED status and reason
```json
{
	"id": 402,
	"idempotencyKey": "po28",
	"status": "FAILED",
	"failureReason": "INSUFFICIENT_FUNDS",
	"created": false,
	"successful": true
}
```
**Failure (200)**
```json
{
	"successful": false,
	"failures": [
		{
			"code": "failure.order.same_account",
			"reason": "Source and target accounts must be different"
		}
	]
}
```
Order statuses: `NEW`, `COMPLETED`, `FAILED`
---

### 2) Lookup Order Details
**GET** `/order/{id}/details`  
**Success (200)**
```json
{
	"successful": true,
	"details": {
		"currencyFrom": "USD",
		"currencyTo": "AMD",
		"amount": 100,
		"rate": 0,
		"status": "FAILED",
		"failedReason": "INSUFFICIENT_FUNDS"
	}
}
```
or 
```json
{
	"successful": true,
	"details": {
		"currencyFrom": "USD",
		"currencyTo": "AMD",
		"amount": 10,
		"rate": 390,
		"status": "COMPLETED",
		"failedReason": null
	}
}
```
**Failure (200)**
```json
{
	"successful": false,
	"failures": [
		{
			"code": "failure.order.not.found",
			"reason": "Order not found."
		}
	]
}
```

## Step‑by‑Step (cURL)

> Replace IDs/UUIDs with values returned by your instance.

### A) Create Customer
```bash
curl -s -X POST http://localhost:8081/customer   -H "Content-Type: application/json"   -d '{ "name":"user", "email":"user@gmail.com", "dateOfBirth":"1998-09-23" }'
# -> { "customerId": 2, "successful": true }
```

### B) Create Two Accounts (different currencies)
```bash
# USD
curl -s -X POST http://localhost:8081/customer/2/account   -H "Content-Type: application/json"   -d '{ "currency":"USD", "balance":250 }'
# -> { "number":"<USD-ACCOUNT-UUID>", "successful": true }

# AMD
curl -s -X POST http://localhost:8081/customer/2/account   -H "Content-Type: application/json"   -d '{ "currency":"AMD", "balance":250 }'
# -> { "number":"<AMD-ACCOUNT-UUID>", "successful": true }
```

### C) Verify Customer & Accounts
```bash
curl -s http://localhost:8081/customer/2/details
curl -s http://localhost:8081/customer/2/account
```

### D) Check Current Rates
```bash
curl -s http://localhost:8081/rates
```

### E) Create an Order (USD → AMD)
```bash
# Replace placeholders with actual account UUIDs from step B
FROM="<USD-ACCOUNT-UUID>"
TO="<AMD-ACCOUNT-UUID>"

curl -s -X POST http://localhost:8081/order   -H "Content-Type: application/json"   -H "Idempotency-Key: 5855"   -d "{ "fromAccountId":"$FROM", "toAccountId":"$TO", "amount":25 }"
# -> { "idempotencyKey":"5855", "status":"COMPLETED", "created":true|false, "successful":true }
```

### F) Re‑check Accounts (balances should change)
- **From** account balance should **decrease by 25** (USD).
- **To** account balance should **increase by 25 × rate(USD→AMD)**.
```bash
curl -s http://localhost:8081/customer/2/account
```

---

## Notes

- Account `number` is a **UUID** for now (number generator may change in future).
- Scheduled job updates rates every `rates.update.interval` milliseconds.
- All calls should go **through the Gateway**.

---

## API Gateway Hardening (must implement)

- **Authentication & Authorization** (JWT/OAuth2/OIDC) on `/forex/api/**` routes.
- **Rate Limiting** (e.g., Spring Cloud Gateway RedisRateLimiter).
- **Trust header**: gateway injects `X-GATEWAY-SECRET`, service rejects direct access without it.
- CORS rules, TLS termination, request size limits, and minimal error leakage.

---
  ⚠️ Discussion — Concurrent Rate Updates

Problem: When creating an order, the FX rate may change at the same time due to scheduler or external updates, causing uncertainty about which rate to apply.

Solution: Store the snapshot rate (with timestamp) directly on the order for auditability, or use optimistic locking with retry to ensure the order always uses the latest rate.
