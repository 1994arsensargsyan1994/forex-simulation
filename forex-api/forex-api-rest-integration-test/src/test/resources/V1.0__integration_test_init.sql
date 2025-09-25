-- Sequences
CREATE SEQUENCE IF NOT EXISTS customer_seq START 1 INCREMENT 50;
SELECT setval('customer_seq', 1, true);

CREATE SEQUENCE IF NOT EXISTS account_seq START 1 INCREMENT 50;
SELECT setval('account_seq', 1, true);

CREATE SEQUENCE IF NOT EXISTS currency_rate_seq START 1 INCREMENT 50;
SELECT setval('currency_rate_seq', 1, true);

CREATE SEQUENCE IF NOT EXISTS fx_order_seq START 1 INCREMENT 50;
SELECT setval('fx_order_seq', 1, true);

-- Customer
CREATE TABLE IF NOT EXISTS customer
(
    id               BIGINT DEFAULT NEXTVAL('customer_seq'),
    username         VARCHAR(255)  NOT NULL UNIQUE,
    email            VARCHAR(255)  NOT NULL UNIQUE,
    date_of_birth    DATE          NOT NULL,
    created_at       TIMESTAMP     NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
    );
ALTER SEQUENCE customer_seq OWNED BY customer.id;

-- Account (per-currency balances)
CREATE TABLE IF NOT EXISTS account
(
    id               BIGINT DEFAULT NEXTVAL('account_seq'),
    number           VARCHAR(50)   NOT NULL UNIQUE,
    version          BIGINT,
    balance          NUMERIC(15,2) NOT NULL DEFAULT 0,
    closing_date     TIMESTAMP,
    is_disabled      BOOLEAN       NOT NULL DEFAULT FALSE,
    currency         VARCHAR(20)   NOT NULL,
    customer_id      BIGINT        NOT NULL,
    created_at       TIMESTAMP     NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
    );
ALTER TABLE account
    ADD CONSTRAINT FK_account_customer_id
        FOREIGN KEY (customer_id) REFERENCES customer(id);
CREATE INDEX IF NOT EXISTS IDX_account_customer_id ON account (customer_id);
CREATE INDEX IF NOT EXISTS IDX_account_currency ON account (currency);
ALTER SEQUENCE account_seq OWNED BY account.id;

-- CurrencyRate (unique pair)
CREATE TABLE IF NOT EXISTS currency_rate
(
    id               BIGINT DEFAULT NEXTVAL('currency_rate_seq'),
    currency_from    VARCHAR(20)   NOT NULL,
    currency_to      VARCHAR(20)   NOT NULL,
    rate             NUMERIC(18,6) NOT NULL,
    last_updated     TIMESTAMP     NOT NULL,
    created_at       TIMESTAMP     NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT UQ_currency_rate_pair UNIQUE (currency_from, currency_to)
    );
ALTER SEQUENCE currency_rate_seq OWNED BY currency_rate.id;

-- fx_order (avoid keyword ORDER)
CREATE TABLE IF NOT EXISTS fx_order
(
    id               BIGINT DEFAULT NEXTVAL('fx_order_seq'),
    account_from_id  BIGINT        NOT NULL,
    account_to_id    BIGINT        NOT NULL,
    amount           NUMERIC(18,4) NOT NULL,
    rate             NUMERIC(18,6) NOT NULL,
    status           VARCHAR(20)   NOT NULL,
    failed_reason    VARCHAR(255),
    idempotency_key  VARCHAR(64)   NOT NULL,
    created_at       TIMESTAMP     NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uq_fx_order_idempotency UNIQUE (idempotency_key)
    );
ALTER TABLE fx_order
    ADD CONSTRAINT FK_fx_order_account_from_id FOREIGN KEY (account_from_id) REFERENCES account(id);
ALTER TABLE fx_order
    ADD CONSTRAINT FK_fx_order_account_to_id FOREIGN KEY (account_to_id) REFERENCES account(id);
CREATE INDEX IF NOT EXISTS IDX_fx_order_from ON fx_order (account_from_id);
CREATE INDEX IF NOT EXISTS IDX_fx_order_to   ON fx_order (account_to_id);
ALTER SEQUENCE fx_order_seq OWNED BY fx_order.id;

-- Insert test data
INSERT INTO customer (id, username, email, date_of_birth, created_at)
VALUES
    (1, 'usertest', 'usertest@example.com', '1994-09-23', NOW()),
    (2, 'usertest1', 'usertest1@example.com', '1995-02-10',NOW());

ALTER SEQUENCE customer_seq restart with 3;


INSERT INTO account (id, number, balance, currency, customer_id, version , created_at)
VALUES
    (1, '58d72261-9e64-419f-9f38-f433166b88a4', 500.00, 'USD', 1,0, NOW()),
    (2, '4a20a2c0-df6d-4f80-ae24-53c16897f1cf', 300.00, 'EUR', 1, 1, NOW()),
    (3, '7b52978a-23f5-4258-99bb-02cb7d4f7a2a', 200000.00, 'AMD', 2, 0,NOW()),
    (4, '8b52978a-23f5-4258-99bb-02cb7d4f7a2o', 200000.00, 'EUR', 2, 1,NOW());


ALTER SEQUENCE account_seq restart with 5;

INSERT INTO currency_rate (id, currency_from, currency_to, rate, last_updated, created_at)
VALUES
    (NEXTVAL('currency_rate_seq'), 'USD', 'AMD', 390.00, NOW(), NOW()),
    (NEXTVAL('currency_rate_seq'), 'AMD', 'USD', 0.0026, NOW(), NOW()),
    (NEXTVAL('currency_rate_seq'), 'USD', 'EUR', 0.92, NOW(), NOW()),
    (NEXTVAL('currency_rate_seq'), 'EUR', 'USD', 1.09, NOW(), NOW());

INSERT INTO fx_order (id, account_from_id, account_to_id, amount, rate, status, idempotency_key, created_at)
VALUES
    (NEXTVAL('fx_order_seq'), 1, 2, 100.00, 0.92, 'COMPLETED', 'idem-001', NOW()),
    (NEXTVAL('fx_order_seq'), 3, 4, 50.00, 390.00, 'NEW', 'idem-002', NOW());