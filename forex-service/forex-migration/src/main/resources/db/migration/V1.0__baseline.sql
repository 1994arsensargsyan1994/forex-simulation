-- Sequences
CREATE SEQUENCE IF NOT EXISTS address_seq START 1 INCREMENT 50;
CREATE SEQUENCE IF NOT EXISTS customer_seq START 1 INCREMENT 50;
CREATE SEQUENCE IF NOT EXISTS account_seq START 1 INCREMENT 50;
CREATE SEQUENCE IF NOT EXISTS currency_rate_seq START 1 INCREMENT 50;
CREATE SEQUENCE IF NOT EXISTS fx_order_seq START 1 INCREMENT 50;

-- Address
CREATE TABLE IF NOT EXISTS address
(
    id               BIGINT DEFAULT NEXTVAL('address_seq'),
    street           VARCHAR(255),
    city             VARCHAR(100)  NOT NULL,
    state            VARCHAR(100),
    zip_code         VARCHAR(20),
    country          VARCHAR(100)  NOT NULL,
    created_at       TIMESTAMP     NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
);
ALTER SEQUENCE address_seq OWNED BY address.id;

-- Customer
CREATE TABLE IF NOT EXISTS customer
(
    id               BIGINT DEFAULT NEXTVAL('customer_seq'),
    username         VARCHAR(255)  NOT NULL UNIQUE,
    email            VARCHAR(255)  NOT NULL UNIQUE,
    date_of_birth    DATE          NOT NULL,
    address_id       BIGINT,
    created_at       TIMESTAMP     NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
);
ALTER TABLE customer
    ADD CONSTRAINT FK_customer_address_id
        FOREIGN KEY (address_id) REFERENCES address(id);
CREATE INDEX IF NOT EXISTS IDX_customer_address_id ON customer (address_id);
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
